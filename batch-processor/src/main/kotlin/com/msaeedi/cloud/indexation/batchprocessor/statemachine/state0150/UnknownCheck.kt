package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0150

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class UnknownCheck : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> = Pair(candidate, Reason.UNKNOWN)

}
