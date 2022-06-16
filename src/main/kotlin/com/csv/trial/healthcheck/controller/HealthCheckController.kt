package com.csv.trial.healthcheck.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController() {
    @GetMapping("/health_check")
    fun register(): ResponseEntity<EmptyResponse> {
        return ResponseEntity.ok(EmptyResponse())
    }
}

class EmptyResponse {

}
