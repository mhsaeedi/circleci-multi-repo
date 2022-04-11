package com.msaeedi.cloud.indexation.orchestrator.util

import aws.sdk.kotlin.runtime.auth.ProfileCredentialsProvider
import aws.sdk.kotlin.services.s3.S3Client
import aws.sdk.kotlin.services.s3.model.ListObjectsV2Request
import aws.sdk.kotlin.services.s3.model.Object
import com.msaeedi.cloud.indexation.orchestrator.constant.Country
import com.msaeedi.cloud.indexation.orchestrator.constant.JobState
import com.msaeedi.cloud.indexation.orchestrator.data.IndexationJob
import kotlinx.coroutines.*
import org.eclipse.microprofile.config.inject.ConfigProperty
import javax.enterprise.context.ApplicationScoped

/**
 * @author renan.arend@msaeedi.com
 * @since  12.11.2021
 */
@ApplicationScoped
class S3Service(
    @ConfigProperty(name = "s3.config.bucket-name") val bucketName: String,
    @ConfigProperty(name = "s3.config.region") val regionConfig: String,
) {
    companion object {
        private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    }

    private val s3Client = S3Client {
        region = regionConfig
        credentialsProvider = ProfileCredentialsProvider("rabbits")
    }


    fun areJobBatchesReady(job: IndexationJob) {
        scope.launch {
            val requestContents = listObjectsByJob(job)
            // TODO: Verify if all needed batches are uploaded
            job.state = if (requestContents.isEmpty()) JobState.ACCEPTED_BY_AGENT else JobState.READY
        }

    }

    suspend fun listObjectsByJob(job: IndexationJob) : List<Object> {
        if (job.id == null) {
            return emptyList()
        }
        val listObjectsRequest = createListObjectRequest(job)
        val listObjectsResponse = s3Client.listObjectsV2(listObjectsRequest)
        val requestContents = listObjectsResponse.contents
        return requestContents ?: emptyList()

    }

    private fun createListObjectRequest(job: IndexationJob): ListObjectsV2Request = with(job) {
        val prefixPath = getPrefix(job)
        return ListObjectsV2Request {
            bucket = bucketName
            prefix = prefixPath
        }
    }

    private fun getPrefix(job: IndexationJob): String = with(job) { "job-$id-${country.name.lowercase()}" }

}
