package com.msaeedi.cloud.indexation.orchestrator.service

import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import javax.enterprise.context.ApplicationScoped
import javax.ws.rs.core.Response.Status

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
@ApplicationScoped
class AgentService {
	fun offerJob(job: IndexationJob): Status {
		return Status.ACCEPTED
	}
}
