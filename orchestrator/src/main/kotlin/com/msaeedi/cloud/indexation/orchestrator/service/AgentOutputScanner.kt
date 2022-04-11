package com.msaeedi.cloud.indexation.orchestrator.service

import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import com.msaeedi.cloud.indexation.orchestrator.repo.JobRepo
import com.msaeedi.cloud.indexation.orchestrator.util.S3Service
import io.quarkus.scheduler.Scheduled
import java.util.logging.Logger
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
@ApplicationScoped
class AgentOutputScanner(
	@Inject internal var jobRepo: JobRepo,
	@Inject internal var jobExecutor: JobExecutor,
    @Inject internal var s3Service: S3Service,
) {
	private val registeredJobs = HashSet(jobRepo.getByStateSortedByDate(JobState.ACCEPTED_BY_AGENT))

	fun register(job: IndexationJob) = registeredJobs.add(job)

    @Scheduled(every = "60s")
	fun scanEveryMinute() {
		if (registeredJobs.isEmpty()) return else scan()
	}


	private fun scan() {
		registeredJobs.forEach(this::agentOutputReady)
	}

	private fun agentOutputReady(job: IndexationJob) {
        when (job.state) {
            JobState.ACCEPTED_BY_AGENT -> acceptedJobProcess(job)
            JobState.AGENT_WORKING -> workingJobProcess(job)
            JobState.READY -> readyJobProcess(job)
            JobState.FAILED_BY_AGENT -> failedJobProcess(job)
            JobState.NEW -> newJobProcess(job)
        }
    }

    private fun acceptedJobProcess(job: IndexationJob) {
        s3Service.areJobBatchesReady(job)
        job.state = JobState.AGENT_WORKING
        log.info("Verifying if Job ${job.id} batches are uploaded on Bucket")
    }

    private fun readyJobProcess(job: IndexationJob) {
        log.info("Job ${job.id} is ready to be executed")
        jobExecutor.execute(job)
        registeredJobs.remove(job)
    }

    private fun failedJobProcess(job: IndexationJob) {
        log.info("Removing Failed by Agent Job ${job.id}")
        registeredJobs.remove(job)
    }

    private fun newJobProcess(job: IndexationJob) {
        log.warning("Job ${job.id} shouldn't be here if it's status is new")
    }

    private fun workingJobProcess(job: IndexationJob) {
        log.info("Something is wrong with Job ${job.id}")
    }

    companion object {
        val log: Logger = Logger.getLogger(this::class.java.name)
    }
}
