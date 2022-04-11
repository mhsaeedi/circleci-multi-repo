package com.msaeedi.cloud.indexation.batchprocessor.shared.constant

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 23.09.21, Thu
 *
 **/
enum class Verdict(s: String) {
	FINAL_TRUE("window pages are final true, manual set (in rule) tagSets are final true/false"),
	FALSE("false after evaluation"),
	TRUE("true after evaluation")
}
