package com.pullout.app.controller

import com.pullout.app.controller.req.RegisterUserReq
import com.pullout.app.controller.res.AccountUserInfoRes
import com.pullout.app.controller.support.GlobalControllerSupport
import com.pullout.app.services.impl.AuthUserService
import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/auth")
class AuthUserController(
    private val authUserService: AuthUserService
) : GlobalControllerSupport() {
    companion object : KLogging()


    // TODO: Swagger 설명 추가
    @PostMapping("/register")
    fun registerUser(@RequestBody @Valid userInfo: RegisterUserReq): ResponseEntity<AccountUserInfoRes> =
        ResponseEntity.status(HttpStatus.CREATED)
            .body(
                AccountUserInfoRes.ofDto(
                    authUserService.registerUser(
                        userId = userInfo.userId,
                        userPassword = userInfo.userPassword,
                        userNickname = userInfo.userNickname,
                        userName = userInfo.userName
                    )
                )
            )
}