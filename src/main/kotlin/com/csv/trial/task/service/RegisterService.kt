package com.csv.trial.task.service

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.csv.trial.task.response.TaskRegisterResponse
import com.csv.trial.task.form.TaskRegisterForm
import com.csv.trial.task.repository.ITaskRepository
import com.csv.trial.task.repository.entity.Task
import com.csv.trial.task.service.lambdaurl.req.LambdaUrlRequest
import com.csv.trial.task.service.lambdaurl.res.LambdaUrlResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import java.util.Date

@Service
class RegisterService(
        private val taskRepository: ITaskRepository,
        private val s3: AmazonS3,
) {
    fun register(form: TaskRegisterForm): TaskRegisterResponse {

        /*
        preSignedUrl生成
         */
        val expiration = Date()
        var expirationInMs = expiration.time
        expirationInMs += (1000 * 60).toLong()
        expiration.time = expirationInMs
        val request = GeneratePresignedUrlRequest("testbucket", form.fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration)
        val preSignedUrl = s3.generatePresignedUrl(request).toURI().toString()
        println("PresingedUrl: $preSignedUrl")

        /*
        DBに登録
         */
        val task = Task(
                name = form.taskName,
                fileId = form.fileName
        )
        val created = taskRepository.saveAndFlush(task)

        /*
        LambdaURLにHTTPS通信
        認証は一旦無視
         */
        val restTemplate = RestTemplate()

        //RequestBody
        val lambdaUrlRequest = LambdaUrlRequest(
                taskId = task.id!!
        )
        //URI
        val uri = ""

        //実行
        val response = restTemplate.postForEntity(uri,lambdaUrlRequest, LambdaUrlResponse::class.java)

        //結果確認
        println(response)

        return TaskRegisterResponse(created, preSignedUrl)
    }
}
