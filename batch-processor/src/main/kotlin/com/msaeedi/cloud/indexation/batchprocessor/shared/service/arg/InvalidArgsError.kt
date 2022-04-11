package com.msaeedi.cloud.indexation.batchprocessor.shared.service.arg

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 27.09.21, Mon
 *
 **/
class InvalidArgsError : Exception {
	constructor(message: String, cause: Exception) : super(message, cause)
	constructor(message: String) : super(message)
}
