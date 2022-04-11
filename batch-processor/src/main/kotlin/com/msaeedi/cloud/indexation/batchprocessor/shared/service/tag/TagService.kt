package com.msaeedi.cloud.indexation.batchprocessor.shared.service.tag

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.TagType
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Tags
import javax.enterprise.context.ApplicationScoped
import kotlin.streams.toList

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 28.09.21, Tue
 *
 **/
@ApplicationScoped
class TagService {
	fun getTags(candidates: MutableCollection<Candidate>): Map<String, Tags> {
		val tagRequests = candidates.stream().parallel().map { TagRequestData(it.tags.commaSeparated, TagType.CATEGORY == it.tagType) }.toList()
		return getTagsFromAgent(tagRequests)

	}

	private fun getTagsFromAgent(tagRequests: List<TagRequestData>): Map<String, Tags> {
		TODO("Impl")
	}

}
