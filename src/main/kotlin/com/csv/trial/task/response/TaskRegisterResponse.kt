package com.csv.trial.task.response

import com.csv.trial.task.repository.entity.Task

class TaskRegisterResponse(
        val id: Int,
        val taskName: String,
        val fileName: String,
        val preSignedUrl: String
) {
    constructor(task: Task, preSignedUrl: String) : this(
            id = task.id!!,
            taskName = task.name!!,
            fileName = task.fileId!!,
            preSignedUrl = preSignedUrl
    )
}
