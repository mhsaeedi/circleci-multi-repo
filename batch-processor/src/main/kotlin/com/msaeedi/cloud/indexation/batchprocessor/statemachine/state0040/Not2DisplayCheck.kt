package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0040

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.TagType
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Fork
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0045.ExistenceCheck
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0050.GoogleIndexableBlockCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class Not2DisplayCheck(
	@Inject internal var googleIndexableBlockCheck: GoogleIndexableBlockCheck,
	@Inject internal var existenceCheck: ExistenceCheck,
) : Check, Fork {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.hasNot2DisplayTag) {
		true -> Pair(candidate, Reason.CONTAINS_NOT_TO_DISPLAY_TAG)
		false -> fork(candidate)

	}

	override fun fork(candidate: Candidate): Pair<Candidate, Reason> = when (TagType.CATEGORY == candidate.tagType) {
		true -> googleIndexableBlockCheck.check(candidate)
		false -> existenceCheck.check(candidate)
	}
}
