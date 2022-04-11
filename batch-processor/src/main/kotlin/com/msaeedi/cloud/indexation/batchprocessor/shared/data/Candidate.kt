package com.msaeedi.cloud.indexation.batchprocessor.shared.data

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.IndexableBlock
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.TagType
import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.WeeklyDaily

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 22.09.21, Wed
 *
 **/
class Candidate {
	val isCanonical = true
	val stillExists = true
	val isUnderProtectionByFormula = false
	val isIndexable = false
	val isPureStyleTag = false
	val hasNonIndexableTagType = false
	val hasNot2DisplayTag = false
	val hasRedirectTag = false
	var hasImageGrouping = false
	var hasDeepestStyleTag = false
	var isTagTypeUrlOrderMapValid = false
	var indexableBlock = IndexableBlock.NOT_SET
	var isWindowPage = false
	var weeklyDaily: WeeklyDaily? = null
	var itemCount = 0
	var shopCount = 0
	var tagType: TagType? = null
	var tags = Tags()
	var hash = ""
}
