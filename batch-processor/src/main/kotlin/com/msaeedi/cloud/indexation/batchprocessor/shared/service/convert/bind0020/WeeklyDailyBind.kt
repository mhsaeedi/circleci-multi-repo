package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0020

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.Binder
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.MalformedLineError
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0030.ItemCountBind
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class WeeklyDailyBind(
	@Inject val itemCountBind: ItemCountBind
) : Binder {

	override fun bind(line: String, candidate: Candidate) {
		if (!isValid(line)) {
			throw MalformedLineError("Line is malformed: $line")
		}
		doBind(line, candidate)
		itemCountBind.bind(line, candidate)

	}

	private fun doBind(line: String, candidate: Candidate) {
		TODO("Implement")
	}

	private fun isValid(line: String): Boolean {
		TODO("Not yet implemented")
	}

}
