package com.pullout.app.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/server")
class ServerInfoController {

    @GetMapping("/health")
    fun healthcheck() : String = "health check !"
}