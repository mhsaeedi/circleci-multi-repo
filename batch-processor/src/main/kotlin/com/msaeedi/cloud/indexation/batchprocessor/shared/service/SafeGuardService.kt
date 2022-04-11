package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 24.09.21, Fri
 *
 **/
@ApplicationScoped
class SafeGuardService {
	fun isInTheProtectionList(candidate: Candidate):Boolean{
		TODO("Implement this")
	}
}
