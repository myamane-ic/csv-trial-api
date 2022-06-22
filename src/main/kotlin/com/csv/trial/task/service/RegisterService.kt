package com.csv.trial.task.service

import com.amazonaws.HttpMethod
import com.amazonaws.services.lambda.AWSLambda
import com.amazonaws.services.lambda.model.InvokeRequest
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.csv.trial.task.form.TaskRegisterForm
import com.csv.trial.task.repository.ITaskRepository
import com.csv.trial.task.repository.entity.Task
import com.csv.trial.task.response.TaskRegisterResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegisterService(
        private val taskRepository: ITaskRepository,
        private val s3: AmazonS3,
        private val lambda: AWSLambda
) {
    fun register(form: TaskRegisterForm): TaskRegisterResponse {

        // preSignedUrl生成
        val expiration = Date()
        var expirationInMs = expiration.time
        expirationInMs += (1000 * 60).toLong()
        expiration.time = expirationInMs
        val request = GeneratePresignedUrlRequest("testbucket", form.fileName)
                .withMethod(HttpMethod.PUT)
                .withExpiration(expiration)
        val preSignedUrl = s3.generatePresignedUrl(request).toURI().toString()
        println("PresingedUrl: $preSignedUrl")

        // DBに登録
        val task = Task(
                name = form.taskName,
                fileId = form.fileName
        )
        val created = taskRepository.saveAndFlush(task)

        //LambdaURLにHTTPS通信
        invokeLambda(taskId = task.id!!)

        return TaskRegisterResponse(created, preSignedUrl)
    }

    /*
    LambdaURLにHTTPS通信
    認証は一旦無視
    */
    fun invokeLambda(taskId: Int) {

        //TODO 設定値に持たせる
        val functionName = "myamane-lambda"
        println(functionName)

        val payload = "{\"taskId\":\"$taskId\"}"
        println(payload)

        //リクエスト生成
        val invokeRequest = InvokeRequest()
                .withFunctionName(functionName)
                //TODO クラスで持たせてデシリアライズ
                .withPayload(payload)

        //実行
        val response = lambda.invoke(invokeRequest)

        //レスポンス
        println(response)
    }
}
