package com.pullout.app.controller

import com.pullout.app.domains.AccountUser
import com.pullout.config.JwtTokenProvider
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    val jwtTokenProvider: JwtTokenProvider,
) {

    @GetMapping("/test")
    fun test(): String {
        val accountUser = AccountUser(
            "test", "test", "test", "test"
        )
        val createAccessToken = jwtTokenProvider.createRefreshToken(accountUser)
        return createAccessToken
    }
}