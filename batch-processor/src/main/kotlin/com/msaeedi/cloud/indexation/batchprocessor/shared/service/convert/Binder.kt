package com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
interface Binder {
	fun bind(line: String, candidate: Candidate)
}
