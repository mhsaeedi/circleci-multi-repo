package com.msaeedi.cloud.indexation.batchprocessor.shared.service.reader

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 27.09.21, Mon
 *
 **/
interface LineReader {
	fun read(): ArrayList<String>
}
