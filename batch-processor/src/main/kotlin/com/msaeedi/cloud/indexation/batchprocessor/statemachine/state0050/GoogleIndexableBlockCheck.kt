package com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0050

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.IndexableBlock
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.Check
import com.msaeedi.cloud.indexation.batchprocessor.statemachine.state0060.NonIndexableTagTypeCheck
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class GoogleIndexableBlockCheck(
	@Inject internal var nonIndexableTagTypeCheck: NonIndexableTagTypeCheck
) : Check {

	override fun check(candidate: Candidate): Pair<Candidate, Reason> = when {
		IndexableBlock.TRUE == candidate.indexableBlock -> Pair(candidate, Reason.FORCE_INDEX_BY_INDEXABLE_BLOCK_TRUE)
		IndexableBlock.FALSE == candidate.indexableBlock -> Pair(candidate, Reason.FORCE_INDEX_BY_INDEXABLE_BLOCK_FALSE)
		else -> nonIndexableTagTypeCheck.check(candidate)
	}


}
