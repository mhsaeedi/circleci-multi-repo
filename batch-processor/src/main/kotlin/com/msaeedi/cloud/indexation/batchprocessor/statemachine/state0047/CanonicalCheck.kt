package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0047

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0080.ItemCountCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 30.09.21, Thu
 *
 **/
@ApplicationScoped
class CanonicalCheck(
	@Inject internal var itemCountCheck: ItemCountCheck,
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.isCanonical) {
		true -> Pair(candidate, Reason.REASON_CANONICAL)
		false -> itemCountCheck.check(candidate)
	}
}
