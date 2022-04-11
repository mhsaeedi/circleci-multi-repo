package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Impression
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class ImpressionService {
	fun hasEnoughImpression(candidate: Candidate, impression: Impression): Boolean = when (impression) {
		Impression.SHORT_TERM -> hasEnoughShortTermImpression(candidate)
		Impression.LONG_TERM -> hasEnoughLongTermImpression(candidate)
		else -> hasEnoughOrganicLongTermImpression(candidate)

	}


	private fun hasEnoughOrganicLongTermImpression(candidate: Candidate): Boolean {
		return true
	}

	private fun hasEnoughLongTermImpression(candidate: Candidate): Boolean {
		return true
	}

	private fun hasEnoughShortTermImpression(candidate: Candidate): Boolean {
		return true
	}
}
