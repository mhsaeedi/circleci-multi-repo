package com.msaeedi.cloud.indexation.batchprocessor.shared.service

import com.msaeedi.cloud.indexation.batchprocessor.shared.constant.Reason
import com.msaeedi.cloud.indexation.batchprocessor.shared.data.Candidate
import java.io.Flushable
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.locks.ReentrantLock
import javax.enterprise.context.ApplicationScoped
import kotlin.concurrent.withLock

/**
 * @author : Mohammad <mohammad.saeedi@msaeedi.com>
 * @since : 28.09.21, Tue
 *
 **/
@ApplicationScoped
class ResultCollector {
	private val resultBucket = ConcurrentHashMap<Candidate, Reason>()
	private val lock = ReentrantLock()
	companion object{
		private const val FLUSH_LIMIT = 100
	}

	fun collect(result: Pair<Candidate, Reason>) {
		lock.withLock {
			resultBucket[result.first] = result.second
			if (resultBucket.size > FLUSH_LIMIT) flush()
		}
	}

	fun flush() {
		TODO("impl")
	}
}
