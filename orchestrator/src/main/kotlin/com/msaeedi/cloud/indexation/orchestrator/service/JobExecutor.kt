package com.msaeedi.cloud.indexation.orchestrator.service

import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import com.msaeedi.cloud.indexation.orchestrator.repo.JobRepo
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
@ApplicationScoped
class JobExecutor(
	@Inject internal var jobRepo: JobRepo,
) {
	private val readyJobs = HashSet(jobRepo.getByStateSortedByDate(JobState.READY))


	fun execute(jobs: Set<IndexationJob>) {

	}

    fun execute(job: IndexationJob) {

    }
}
