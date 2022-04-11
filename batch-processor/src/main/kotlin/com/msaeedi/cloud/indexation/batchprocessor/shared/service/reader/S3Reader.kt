package com.msaeedi.cloud.indexation.batchprocessor.shared.service.reader

import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 27.09.21, Mon
 *
 **/
@ApplicationScoped
class S3Reader: LineReader {
	override fun read(): ArrayList<String> {
		TODO("Not yet implemented")
	}
}
