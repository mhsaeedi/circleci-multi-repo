package com.msaeedi.cloud.indexation.orchestrator.constant

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 07.10.21, Thu
 *
 **/
enum class JobState {
	NEW,
	FAILED_BY_AGENT,
	ACCEPTED_BY_AGENT,
    AGENT_WORKING,
	READY,
}
