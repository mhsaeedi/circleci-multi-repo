package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Country
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 29.09.21, Wed
 *
 **/
@ApplicationScoped
class ConfigService {
	fun minAllowedItemCount(): Int {
		return 5
	}

	fun country(): Country {
		return Country.DE
	}
}
