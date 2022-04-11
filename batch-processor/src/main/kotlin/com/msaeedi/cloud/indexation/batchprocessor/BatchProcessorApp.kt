package com.msaeedi.cloud.indexation.batchprocessor

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.ExitCode
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.arg.ArgsService
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.arg.InvalidArgsError
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.donald.DonaldApi
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.donald.DonaldApiError
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0000.StateMachine
import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.QuarkusApplication
import io.quarkus.runtime.annotations.QuarkusMain
import org.jboss.logging.Logger
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@msaeedi.com>
 * @since : 21.09.21, Tue
 */
@QuarkusMain(name = "StateMachineRunner")
class BatchProcessorApp(
	@Inject val donaldApi: DonaldApi,
	@Inject val stateMachine: StateMachine,
	@Inject val argsService: ArgsService,
) : QuarkusApplication {
	companion object {
		val LOGGER = Logger.getLogger(BatchProcessorApp::class.java.name)!!
	}

	override fun run(args: Array<String>): Int {

		try {
			argsService.validateAndSet(args)
			donaldApi.assureDonaldIsUpToDate()
		} catch (e: InvalidArgsError) {
			return exitProcess(e, ExitCode.INVALID_ARGS.exitCode, args)
		} catch (e: DonaldApiError) {
			return exitProcess(e, ExitCode.DONALD_DATA_NOT_UP_TO_DATE.exitCode, args)
		} catch (e: Exception) {
			return exitProcess(e, ExitCode.UNEXPECTED_ERROR.exitCode, args)
		}
		stateMachine.fire()
		val argsAsString = args.joinToString(separator = ",")
		LOGGER.info("Start batch processing with args: $argsAsString")
		Quarkus.waitForExit()
		LOGGER.info("End batch processing successfully with args: $argsAsString}")
		return ExitCode.PROCESS_COMPLETED_SUCCESSFULLY.exitCode
	}

	private fun exitProcess(e: Exception, exitCode: Int, args: Array<String>): Int {
		LOGGER.error("Error: ${e::class.java.simpleName} happened for process with args: ${args.joinToString(separator = ",")}")
		LOGGER.debug(e.message, e)
		Quarkus.waitForExit()
		return exitCode

	}

}
