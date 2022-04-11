package com.msaeedi.cloud.indexation.batchprocessor.shared.service.reader

import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 27.09.21, Mon
 *
 **/
@ApplicationScoped
class DevReader : LineReader {
	override fun read(): ArrayList<String> {
		return arrayListOf("hello")
	}
}
