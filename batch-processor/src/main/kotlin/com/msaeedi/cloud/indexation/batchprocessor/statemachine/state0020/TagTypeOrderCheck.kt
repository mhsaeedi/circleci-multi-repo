package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0020

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0030.RedirectTagCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class TagTypeOrderCheck(
	@Inject internal var redirectTagCheck: RedirectTagCheck
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.isTagTypeUrlOrderMapValid) {
		false -> Pair(candidate, Reason.REASON_INVALID_TAG_SET)
		true -> redirectTagCheck.check(candidate)
	}

}
