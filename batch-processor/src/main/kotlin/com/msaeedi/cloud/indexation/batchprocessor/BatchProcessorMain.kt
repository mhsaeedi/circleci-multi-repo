package com.msaeedi.cloud.indexation.batchprocessor

import io.quarkus.runtime.Quarkus
import io.quarkus.runtime.annotations.QuarkusMain


/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@msaeedi.com>
 * @since : 21.09.21, Tue
 */
@QuarkusMain
object BatchProcessorMain {
	@JvmStatic
	fun main(args: Array<String>) {
		Quarkus.run(BatchProcessorApp::class.java, *args)
	}
}
