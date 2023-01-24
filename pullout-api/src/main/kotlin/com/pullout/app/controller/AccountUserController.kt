package com.pullout.app.controller

import com.pullout.app.services.AccountUserService
import mu.KLogging
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/v1/users")
class AccountUserController(val accountUserService: AccountUserService) {
    companion object : KLogging()
}