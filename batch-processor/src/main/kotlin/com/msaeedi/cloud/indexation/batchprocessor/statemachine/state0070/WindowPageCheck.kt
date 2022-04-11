package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0070

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0080.ItemCountCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class WindowPageCheck(
	@Inject internal var itemCountCheck: ItemCountCheck
) : Check {


	override fun check(candidate: Candidate) = when (candidate.isWindowPage) {
		true -> Pair(candidate, Reason.WINDOW_PAGE)
		false -> itemCountCheck.check(candidate)
	}

}
