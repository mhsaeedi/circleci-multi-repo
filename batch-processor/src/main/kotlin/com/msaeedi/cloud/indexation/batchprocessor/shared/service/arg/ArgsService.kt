package com.msaeedi.cloud.indexation.batchprocessor.shared.service.arg

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.ArgData
import org.jboss.logging.Logger
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 27.09.21, Mon
 *
 **/
@ApplicationScoped
class ArgsService {
	var argData: ArgData? = null

	private val expectedArgsSize = 6
	private val LOGGER = Logger.getLogger(ArgsService::class.java.name)
	fun validateAndSet(args: Array<String>) {
		checkEmpty(args)
		checkLength(args)
		val from = extractArg("--from", "-f", args)
		val to = extractArg("--to", "-t", args)
		val processId = extractArg("--id", "-i", args)
		argData = ArgData(from, to, processId)
	}

	private fun checkEmpty(args: Array<String>) {
		if (args.isEmpty()) throw InvalidArgsError("Args must not be empty")
	}

	private fun extractArg(name: String, shorthandName: String, args: Array<String>): Int {
		for (i in 0..4 step 2)
			if (args[i] == name || args[i] == shorthandName)
				return parse2Int(args[i], args[i + 1])
		throw InvalidArgsError("Arg: $name $shorthandName, not found")
	}

	private fun parse2Int(name: String, value: String): Int = try {
		value.toInt()
	} catch (e: NumberFormatException) {
		throw InvalidArgsError("Invalid arg: $name, value: $value", e)
	}

	private fun checkLength(args: Array<String>) {
		if (args.size != expectedArgsSize) throw InvalidArgsError("Invalid length of args, expected: $expectedArgsSize, actual: ${args.size}")
	}

}

