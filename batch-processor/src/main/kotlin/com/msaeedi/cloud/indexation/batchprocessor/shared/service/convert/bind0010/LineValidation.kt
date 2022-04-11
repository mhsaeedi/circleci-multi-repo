package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0010

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.Binder
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.MalformedLineError
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0020.WeeklyDailyBind
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class LineValidation(
	@Inject val weeklyDailyBind: WeeklyDailyBind
) : Binder {

	override fun bind(line: String, candidate: Candidate) {
		if (!isValid(line)) {
			throw MalformedLineError("Line is malformed: $line")
		}
		weeklyDailyBind.bind(line, candidate)

	}

	private fun isValid(line: String): Boolean {
		TODO("Not yet implemented")
	}
}
