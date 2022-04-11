package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0100.rule00.ShortTermImpressionCheck
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0110.NonIndexableTrafficRulesCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class IndexableTrafficRulesCheck(
	@Inject internal var nonIndexableTrafficRulesCheck: NonIndexableTrafficRulesCheck,
	@Inject internal var shortTermImpressionCheck: ShortTermImpressionCheck,
):Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when(candidate.isIndexable){
		true -> shortTermImpressionCheck.check(candidate)
		false -> nonIndexableTrafficRulesCheck.check(candidate)
	}
}
