package com.csv.trial.task.service

import com.csv.trial.task.response.TaskRegisterResponse
import com.csv.trial.task.form.TaskRegisterForm
import com.csv.trial.task.repository.ITaskRepository
import com.csv.trial.task.repository.entity.Task
import org.springframework.stereotype.Service

@Service
class RegisterService(
        private val taskRepository: ITaskRepository
) {
    fun register(form: TaskRegisterForm): TaskRegisterResponse {

        //TODO preSignedUrl生成

        val task = Task(
                name = form.taskName,
                //TODO preSignedUrlを作る
                fileId = form.fileName
        )

        val created = taskRepository.saveAndFlush(task)

        return TaskRegisterResponse(created)
    }
}
