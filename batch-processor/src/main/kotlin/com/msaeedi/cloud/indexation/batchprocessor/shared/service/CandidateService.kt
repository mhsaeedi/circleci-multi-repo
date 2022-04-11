package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.convert.Converter
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.reader.DevReader
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.reader.S3Reader
import com.msaeedi.cloud.indexation.batchprocessor.shared.service.tag.TagService
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.util.concurrent.ConcurrentHashMap
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 28.09.21, Tue
 *
 **/
@ApplicationScoped
class CandidateService(
	@Inject var converter: Converter,
	@Inject var devReader: DevReader,
	@Inject var s3Reader: S3Reader,
	@Inject var tagService: TagService,
	@ConfigProperty(name = "batch.processor.is-dev") val isDev: Boolean,
) {

	fun getCandidates(): MutableCollection<Candidate> {
		val candidates = ConcurrentHashMap<String, Candidate>()
		val reader = if (isDev) devReader else s3Reader
		reader.read().stream().parallel().map(converter::convert).forEach { candidates[it.tags.commaSeparated] = it }
		tagService.getTags(candidates.values).entries.stream().parallel().forEach { candidates[it.key]?.tags = it.value }
		return candidates.values
	}
}
