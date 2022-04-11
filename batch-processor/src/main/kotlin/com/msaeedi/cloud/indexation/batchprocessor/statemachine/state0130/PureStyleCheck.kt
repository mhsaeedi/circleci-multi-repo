package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0130

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0140.SafeGuardCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class PureStyleCheck(
	@Inject internal var safeGuardCheck: SafeGuardCheck,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.isPureStyleTag) {
		true -> Pair(candidate, Reason.SKIPPED_TRAFFIC_RULES_DUE_TO_PURE_STYLE_TAG_URL)
		false -> safeGuardCheck.check(candidate)
	}
}
