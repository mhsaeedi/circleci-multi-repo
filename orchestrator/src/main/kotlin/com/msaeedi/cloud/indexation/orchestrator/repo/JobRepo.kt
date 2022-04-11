package com.msaeedi.cloud.indexation.orchestrator.repo

import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
@ApplicationScoped
class JobRepo {
	fun save(job: IndexationJob): IndexationJob = job
	fun updateState(jobId: Int, jobState: JobState) {

	}

	fun updateState(jobIds: List<Int>, jobState: JobState) {

	}

	fun getByStateSortedByDate(jobState: JobState):Collection<IndexationJob> = mutableListOf()
}
