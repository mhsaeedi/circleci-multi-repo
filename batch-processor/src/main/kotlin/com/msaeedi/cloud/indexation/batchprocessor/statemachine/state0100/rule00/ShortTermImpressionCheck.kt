package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.rule00

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Impression
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ImpressionService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.rule10.LongTermImpressionCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class ShortTermImpressionCheck(
	@Inject internal var impressionService: ImpressionService,
	@Inject internal var longTermImpressionCheck: LongTermImpressionCheck,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> =
		when (impressionService.hasEnoughImpression(candidate, Impression.SHORT_TERM)) {
			true -> Pair(candidate, Reason.REASON_TRAFFIC_ATLEASTXPAGEIMPRESSIONSPAGEONELAST35DAYS)
			false -> longTermImpressionCheck.check(candidate)
		}

}
