package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0080

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ItemCountService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0090.HighBounceRateCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class ItemCountCheck(
	@Inject internal var highBounceRateCheck: HighBounceRateCheck,
	@Inject internal var itemCountService: ItemCountService,
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (itemCountService.hasEnoughItems(candidate)) {
		false -> Pair(candidate, Reason.INADEQUATE_ITEM_COUNT)
		true -> highBounceRateCheck.check(candidate)
	}


}
