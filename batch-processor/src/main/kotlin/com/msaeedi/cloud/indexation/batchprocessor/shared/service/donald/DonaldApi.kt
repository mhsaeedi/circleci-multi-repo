package com.msaeedi.cloud.indexation.batchprocessor.shared.service.donald

import com.msaeedi.cloud.indexation.batchprocessor.shared.service.ConfigService
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject


/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 22.09.21, Wed
 *
 **/
@ApplicationScoped
class DonaldApi(
	@Inject internal var configService: ConfigService,
	@ConfigProperty(name = "batch.processor.donald-available") donaldAvailable: String
) {

	fun assureDonaldIsUpToDate(): Boolean = isDonaldDataAvailable()


	private fun isDonaldDataAvailable(): Boolean {
		configService.country()

		return true
	}


}
