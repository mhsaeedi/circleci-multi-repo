package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0110

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Impression
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ImpressionService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class NonIndexableTrafficRulesCheck(
	@Inject internal var impressionService: ImpressionService,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> =
		when (impressionService.hasEnoughImpression(candidate, Impression.SHORT_TERM)) {
			true -> Pair(candidate, Reason.REASON_TRAFFIC_REINDEXED_ATLEASTXPAGEIMPRESSIONSPAGEONELAST35DAYS)
			false -> Pair(candidate, Reason.REASON_TRAFFIC_REINDEXED_LESSTHENXPAGEIMPRESSIONSPAGEONELAST35DAYS)
		}


}
