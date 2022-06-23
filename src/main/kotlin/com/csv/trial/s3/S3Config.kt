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

    @Value("\${aws.accessKey}")
    val accessKey: String = ""

    @Value("\${aws.secretKey}")
    val secretKey: String = ""

    @Value("\${aws.region}")
    val region: String? = ""

    @Value("\${aws.s3.endPoint}")
    val endPoint: String = ""

    @Bean
    fun getS3Client(): AmazonS3 {

        //実際にはもっと設定要る
        val credentials = BasicAWSCredentials(accessKey, secretKey)
        val endpointConfiguration = EndpointConfiguration(endPoint, region)

        return AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .build()
    }
}