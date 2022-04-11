package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0120

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ShopCountService
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0130.PureStyleCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class ShopCountCheck(
	@Inject var shopCountService: ShopCountService,
	@Inject var pureStyleCheck: PureStyleCheck,
) : Check {
	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (shopCountService.hasEnoughShops(candidate)) {
		false.and(0 == candidate.shopCount) -> Pair(candidate, Reason.LESS_THAN_MIN_ITEM_COUNT)
		false -> Pair(candidate, Reason.MIN_SHOP_COUNT)
		true -> pureStyleCheck.check(candidate)
	}

}
