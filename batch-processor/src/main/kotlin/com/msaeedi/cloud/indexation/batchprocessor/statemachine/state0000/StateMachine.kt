package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0000

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.TagType
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.CandidateService
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ResultCollector
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Fork
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0010.DeepestTagCheck
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0030.RedirectTagCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class StateMachine(
	@Inject internal var candidateService: CandidateService,
	@Inject internal var deepestTagCheck: DeepestTagCheck,
	@Inject internal var redirectTagCheck: RedirectTagCheck,
	@Inject internal var resultCollector: ResultCollector,
) : Fork {

	fun fire() = candidateService.getCandidates().stream().parallel().map(::fork).forEach { resultCollector.collect(it) }
		.also { resultCollector.flush() }

	override fun fork(candidate: Candidate): Pair<Candidate, Reason> = when (TagType.CATEGORY == candidate.tagType) {
		true -> deepestTagCheck.check(candidate)
		false -> redirectTagCheck.check(candidate)
	}

}
