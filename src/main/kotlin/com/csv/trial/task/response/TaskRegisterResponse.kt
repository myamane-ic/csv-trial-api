package com.csv.trial.task.response

import com.csv.trial.task.repository.entity.Task

class TaskRegisterResponse(
        val id: Int,
        val taskName: String,
        val preSignedUrl: String
) {
    constructor(task: Task) : this(
            id = task.id!!,
            taskName = task.name!!,
            preSignedUrl = task.fileId!!
    )
}
