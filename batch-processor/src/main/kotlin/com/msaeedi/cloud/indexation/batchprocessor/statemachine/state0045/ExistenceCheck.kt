package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0045

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.TagType
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Fork
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0047.CanonicalCheck
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0080.ItemCountCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 30.09.21, Thu
 *
 **/
@ApplicationScoped
class ExistenceCheck(
	@Inject internal var itemCountCheck: ItemCountCheck,
	@Inject internal var canonicalCheck: CanonicalCheck,
) : Check, Fork {

	companion object {
		val canonicals = hashSetOf(TagType.BRAND, TagType.MERCHANDISE, TagType.SHOP, TagType.SUB_MERCHANDISE)
	}

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.stillExists) {
		false -> Pair(candidate, Reason.NOT_EXISTS)
		true -> fork(candidate)

	}

	override fun fork(candidate: Candidate): Pair<Candidate, Reason> = when (canonicals.contains(candidate.tagType)) {
		true -> canonicalCheck.check(candidate)
		false -> itemCountCheck.check(candidate)
	}
}
