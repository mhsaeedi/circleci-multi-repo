package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0090

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.BounceRateService
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.SafeGuardService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.IndexableTrafficRulesCheck
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0120.ShopCountCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class HighBounceRateCheck(
	@Inject internal var safeGuardService: SafeGuardService,
	@Inject internal var bounceRateService: BounceRateService,
	@Inject internal var shopCountCheck: ShopCountCheck,
	@Inject internal var indexableTrafficRulesCheck: IndexableTrafficRulesCheck,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> =
		when {
			safeGuardService.isInTheProtectionList(candidate) -> shopCountCheck.check(candidate)
			bounceRateService.hasHighBounceRate(candidate) -> Pair(candidate, Reason.HIGH_BOUNCE_RATE)
			candidate.isPureStyleTag.or(candidate.isUnderProtectionByFormula) -> shopCountCheck.check(candidate)
			else -> indexableTrafficRulesCheck.check(candidate)
		}


}
