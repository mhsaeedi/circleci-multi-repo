package com.msaeedi.cloud.indexation.orchestrator.resource

import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import com.msaeedi.cloud.indexation.orchestrator.service.JobService
import org.eclipse.microprofile.openapi.annotations.Operation
import org.eclipse.microprofile.openapi.annotations.media.Content
import org.eclipse.microprofile.openapi.annotations.media.Schema
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import org.jboss.logging.Logger
import javax.inject.Inject
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.Response.Status

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 06.10.21, Wed
 *
 **/
@Path("api/v1/job")
@Tag(name = "Job", description = "Job related operations")
class JobResource(
	@Inject internal var jobService: JobService,
) {


	@Operation(summary = "Adds a new job", description = "Returns the added job")
	@APIResponses(
		value = [
			APIResponse(
				responseCode = "201",
				description = "If job adds successfully",
				content = [Content(
					mediaType = MediaType.APPLICATION_JSON,
					schema = Schema(implementation = IndexationJob::class)
				)]
			),
			APIResponse(responseCode = "400", description = "If validation fails"),
			APIResponse(responseCode = "406", description = "If cloud-indexation-agent rejects the job to fails"),
			APIResponse(responseCode = "500", description = "If an unexpected error happens"),
		]
	)
	@POST
	fun add(job: IndexationJob): Response = try {
		if (jobService.isInvalid(job)) Response.status(Status.BAD_REQUEST).build()
		val addedJob = jobService.addAndStart(job)
		when (JobState.ACCEPTED_BY_AGENT == addedJob.state) {
			true -> Response.status(Status.CREATED).entity(addedJob).build()
			false -> Response.status(Status.NOT_ACCEPTABLE).entity(addedJob).build()
		}
	} catch (e: Exception) {
		log.error("${e.javaClass.simpleName} happened in add(job:Job), for job: $job")
		log.debug(e.message, e)
		Response.status(Status.INTERNAL_SERVER_ERROR).build()
	}

	companion object {
		val log: Logger = Logger.getLogger(JobResource.javaClass.name)
	}

}
