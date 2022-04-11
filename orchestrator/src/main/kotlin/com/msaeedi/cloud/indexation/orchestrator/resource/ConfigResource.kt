package com.msaeedi.cloud.indexation.orchestrator.resource

import com.msaeedi.cloud.indexation.orchestrator.data.Config
import com.msaeedi.cloud.indexation.orchestrator.service.ConfigService
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
 * @since : 07.10.21, Thu
 *
 **/
@Path("api/v1/config")
@Tag(name = "Config", description = "Config related operations")
class ConfigResource(
	@Inject internal var service: ConfigService,
) {
	@Operation(summary = "Adds a new config", description = "Returns the added config")
	@APIResponses(
		value = [
			APIResponse(
				responseCode = "201",
				description = "If config adds successfully",
				content = [Content(
					mediaType = MediaType.APPLICATION_JSON,
					schema = Schema(implementation = Config::class)
				)]
			),
			APIResponse(responseCode = "400", description = "If validation fails"),
			APIResponse(responseCode = "500", description = "If an unexpected error happens"),
		]
	)
	@POST
	fun add(config: Config): Response = try {
		if (service.isInvalid(config)) Response.status(Status.BAD_REQUEST).build()
		Response.status(Status.CREATED).entity(service.add(config)).build()
	} catch (e: Exception) {
		log.error("${e.javaClass.simpleName} happened in add(config:Config), for config: $config")
		log.debug(e.message, e)
		Response.status(Status.INTERNAL_SERVER_ERROR).build()
	}

	companion object {
		val log: Logger = Logger.getLogger(ConfigResource.javaClass.name)
	}
}
