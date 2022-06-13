package com.csv.trial.task.repository

import com.csv.trial.task.repository.entity.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ITaskRepository : JpaRepository<Task, Int>