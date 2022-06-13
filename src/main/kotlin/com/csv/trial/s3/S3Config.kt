package com.csv.trial.s3

import com.amazonaws.ClientConfiguration
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class S3Config {

    @Value("\${s3.accessKey}")
    val accessKey: String = ""

    @Value("\${s3.secretKey}")
    val secretKey: String = ""

    @Value("\${s3.region}")
    val region: String? = ""

    @Value("\${s3.endPoint}")
    val endPoint: String = ""

    @Bean
    fun getClient(): AmazonS3 {

        //実際にはもっと設定要る
        val credentials = BasicAWSCredentials(accessKey, secretKey)
        val endpointConfiguration = EndpointConfiguration(endPoint, region)

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }
}