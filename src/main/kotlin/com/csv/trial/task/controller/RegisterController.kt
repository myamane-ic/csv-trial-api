package com.csv.trial.task.controller

import com.csv.trial.task.form.TaskRegisterForm
import com.csv.trial.task.response.TaskRegisterResponse
import com.csv.trial.task.service.RegisterService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class RegisterController(
        private val registerService: RegisterService
) {

    @PostMapping("/task")
    fun register(@RequestBody form: TaskRegisterForm): ResponseEntity<TaskRegisterResponse> {
        return ResponseEntity.ok(registerService.register(form))
    }
}