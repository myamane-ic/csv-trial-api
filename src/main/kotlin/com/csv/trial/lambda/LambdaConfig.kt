package com.csv.trial.lambda

import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.lambda.AWSLambda
import com.amazonaws.services.lambda.AWSLambdaClientBuilder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LambdaConfig {

    @Value("\${s3.accessKey}")
    val accessKey: String = ""

    @Value("\${s3.secretKey}")
    val secretKey: String = ""

    @Value("\${s3.region}")
    val region: String? = ""

    //参考: https://docs.aws.amazon.com/sdk-for-java/v1/developer-guide/examples-lambda.html
    @Bean
    fun getLambdaClient(): AWSLambda {

        //実際にはもっと設定要る
        val credentials = BasicAWSCredentials(accessKey, secretKey)

        return AWSLambdaClientBuilder.standard()
                .withCredentials(AWSStaticCredentialsProvider(credentials))
                .withRegion(region).build()
    }
}