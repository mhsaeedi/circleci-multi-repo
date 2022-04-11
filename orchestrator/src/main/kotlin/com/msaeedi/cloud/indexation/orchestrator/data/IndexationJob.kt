package com.msaeedi.cloud.indexation.orchestrator.data

import com.msaeedi.cloud.indexation.orchestrator.constant.Country
import com.msaeedi.cloud.indexation.orchestrator.constant.JobState

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 06.10.21, Wed
 *
 **/
class IndexationJob {
	var id: Int? = null
    var country: Country = Country.DE
	var configId:Int? = null
	var patchId:Int? = null
	var emailGroupId:Int? = null
	var state = JobState.NEW
}
