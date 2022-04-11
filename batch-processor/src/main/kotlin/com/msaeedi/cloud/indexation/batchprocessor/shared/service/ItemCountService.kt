package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class ItemCountService {
	fun hasEnoughItems(candidate: Candidate): Boolean {
		return true
	}
}
