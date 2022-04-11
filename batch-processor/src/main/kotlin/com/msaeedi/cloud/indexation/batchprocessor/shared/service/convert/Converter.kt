package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.bind0010.LineValidation
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class Converter(
	@Inject private val lineValidation: LineValidation
) {
	fun convert(line: String): Candidate {

		val candidate = Candidate()
		lineValidation.bind(line, candidate)
		return candidate
	}
}
