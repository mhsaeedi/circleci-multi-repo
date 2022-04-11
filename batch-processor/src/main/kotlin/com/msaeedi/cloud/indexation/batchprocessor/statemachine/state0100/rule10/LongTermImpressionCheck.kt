package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.rule10

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Impression
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ImpressionService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.rule20.OrganicLongTermImpressionCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class LongTermImpressionCheck(
	@Inject internal var impressionService: ImpressionService,
	@Inject internal var organicLongTermImpressionCheck: OrganicLongTermImpressionCheck,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> =
		when (impressionService.hasEnoughImpression(candidate, Impression.LONG_TERM)) {
			true -> Pair(candidate, Reason.REASON_TRAFFIC_ATLEAST200PAGEIMPRESSIONSPAGEONELAST365DAYS)
			false -> organicLongTermImpressionCheck.check(candidate)
		}

}
