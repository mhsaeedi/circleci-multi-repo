package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0010

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0020.TagTypeOrderCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class DeepestTagCheck(
	@Inject internal var tagTypeOrderCheck: TagTypeOrderCheck
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when (candidate.hasDeepestStyleTag) {
		false -> Pair(candidate, Reason.NULL_DEEPEST_TAG)
		true -> tagTypeOrderCheck.check(candidate)
	}


}
