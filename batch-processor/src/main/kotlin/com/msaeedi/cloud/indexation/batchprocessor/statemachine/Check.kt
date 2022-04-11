package com.msaeedi.cloud.indexation.batchprocessor.statemachine

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate


/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
interface Check {
	fun check(candidate: Candidate): Pair<Candidate, Reason>
}
