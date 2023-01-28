package com.pullout.app.controller.healthCheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController {

    @GetMapping("/healthcheck")
    fun healthcheck() : String = "health check !"
}