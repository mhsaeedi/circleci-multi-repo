package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0060

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0070.WindowPageCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class NonIndexableTagTypeCheck(
	@Inject internal var windowPageCheck: WindowPageCheck
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.hasNonIndexableTagType) {
		true -> Pair(candidate, Reason.CONTAINS_NON_INDEXABLE_TAG)
		false -> windowPageCheck.check(candidate)
	}

}
