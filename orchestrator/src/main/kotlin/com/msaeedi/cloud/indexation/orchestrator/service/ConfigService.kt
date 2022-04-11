package com.msaeedi.cloud.indexation.orchestrator.service

import com.msaeedi.cloud.indexation.orchestrator.data.Config
import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
@ApplicationScoped
class ConfigService {
	fun isInvalid(config: Config): Boolean {
		return true
	}

	fun add(config: Config): Config {
		return Config()
	}
}
