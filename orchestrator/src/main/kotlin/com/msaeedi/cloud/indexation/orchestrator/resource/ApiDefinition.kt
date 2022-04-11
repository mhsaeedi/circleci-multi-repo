package com.msaeedi.cloud.indexation.orchestrator.resource

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition
import org.eclipse.microprofile.openapi.annotations.info.Contact
import org.eclipse.microprofile.openapi.annotations.info.Info
import org.eclipse.microprofile.openapi.annotations.info.License
import org.eclipse.microprofile.openapi.annotations.tags.Tag
import javax.ws.rs.core.Application

/**
 * @author : Mohammad <mohammad.saeedi></mohammad.saeedi>@msaeedi.com>
 * @since : 06.10.21, Wed
 */
@OpenAPIDefinition(
	info = Info(
		title = "Orchestrator API",
		version = "1.0",
		contact = Contact(name = "Orchestrator API Support", email = "rabbits-dev@msaeedi.com"),
		license = License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")
	)
)
class ApiDefinition : Application()
