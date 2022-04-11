package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0050

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.Binder
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.MalformedLineError
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0060.TagSetBind
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class TagTypeBind(
	@Inject val tagSetBind: TagSetBind
) : Binder {

	override fun bind(line: String, candidate: Candidate) {
		if (!isValid(line)) {
			throw MalformedLineError("Line is malformed: $line")
		}
		doBind(line, candidate)
		tagSetBind.bind(line, candidate)

	}

	private fun doBind(line: String, candidate: Candidate) {
		TODO("Implement")
	}

	private fun isValid(line: String): Boolean {
		TODO("Not yet implemented")
	}
}
