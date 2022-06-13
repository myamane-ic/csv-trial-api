package com.csv.trial.task.repository.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "task")
class Task(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Int?= null,
        var name: String? = null,
        var fileId: String? = null,
)