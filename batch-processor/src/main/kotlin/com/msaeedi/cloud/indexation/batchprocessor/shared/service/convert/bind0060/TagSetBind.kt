package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0060

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.Binder
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.MalformedLineError
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class TagSetBind: Binder {

	override fun bind(line: String, candidate: Candidate) {
		if (!isValid(line)) {
			throw MalformedLineError("Line is malformed: $line")
		}
		doBind(line, candidate)
	}

	private fun doBind(line: String, candidate: Candidate) {
		TODO("Implement")
	}

	private fun isValid(line: String): Boolean {
		TODO("Not yet implemented")
	}
}
