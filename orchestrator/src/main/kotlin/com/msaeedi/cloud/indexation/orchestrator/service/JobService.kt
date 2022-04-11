package com.msaeedi.cloud.indexation.orchestrator.service

import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import com.msaeedi.cloud.indexation.orchestrator.repo.JobRepo
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject
import javax.ws.rs.core.Response.Status

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 06.10.21, Wed
 *
 **/
@ApplicationScoped
class JobService(
	@Inject internal var jobRepo: JobRepo,
	@Inject internal var agentService: AgentService,
	@Inject internal var outputScanner: AgentOutputScanner,
) {
	fun isInvalid(job: IndexationJob): Boolean {
		return true
	}

	fun addAndStart(job: IndexationJob): IndexationJob {
		val addedJob = jobRepo.save(job)
		val jobState = if (Status.ACCEPTED == agentService.offerJob(addedJob)) {
			outputScanner.register(addedJob)
			JobState.ACCEPTED_BY_AGENT
		} else JobState.FAILED_BY_AGENT
        addedJob.state = jobState
        jobRepo.updateState(addedJob.id!!, jobState)
		return addedJob
	}
}
