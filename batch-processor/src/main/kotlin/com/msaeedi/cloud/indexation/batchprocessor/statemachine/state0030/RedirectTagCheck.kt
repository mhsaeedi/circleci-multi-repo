package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0030

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0040.Not2DisplayCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class RedirectTagCheck(
	@Inject internal var not2DisplayCheck: Not2DisplayCheck
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.hasRedirectTag) {
		true -> Pair(candidate, Reason.REASON_REDIRECT_TAG)
		false -> not2DisplayCheck.check(candidate)
	}
}
