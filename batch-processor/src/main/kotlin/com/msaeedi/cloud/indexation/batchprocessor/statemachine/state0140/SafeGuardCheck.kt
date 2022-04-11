package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0140

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.SafeGuardService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0150.UnknownCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class SafeGuardCheck(
	@Inject internal var unknownCheck: UnknownCheck,
	@Inject internal var safeGuardService: SafeGuardService,
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> =
		when (safeGuardService.isInTheProtectionList(candidate).or(candidate.isUnderProtectionByFormula)) {
			true -> Pair(candidate, Reason.TRAFFIC_WELPENSCHUTZ)
			false -> unknownCheck.check(candidate)
		}

}
