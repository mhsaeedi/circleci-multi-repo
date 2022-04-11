package com.msaeedi.cloud.indexation.batchprocessor.shared.data

import javax.enterprise.context.ApplicationScoped

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
@ApplicationScoped
class Config() {
	var healthThreshold: Double? = null
	var increaseThreshold: Double? = null
	var googleIndexableMinItemCount = 0
	var oldGuardTriggerThreshold: Int? = null
	var oldGuardFailThreshold: Int? = null
	var oldGuardFailThresholdForRedirectedTags: Int? = null
	var minPageImpressionsLast35DaysThreshold: Int? = null
	var minPageImpressionsLast365DaysThreshold: Int? = null
	var minimumSeoLandings365DaysThreshold: Int? = null
	var minShopCountThreshold = 0
}
