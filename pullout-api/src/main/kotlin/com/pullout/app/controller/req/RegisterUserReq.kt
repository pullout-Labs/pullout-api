package com.pullout.app.controller.req

import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import kotlin.math.max
import kotlin.math.min

data class RegisterUserReq(
    @field:NotNull(message = "userId cannot be null")
    @field:Size(min = 6, max = 20, message = "userId Size Error")
    val userId: String,
    @field:NotNull(message = "userPassword cannot be null")
    @field:Size(min = 8, max = 20, message ="userPassword Size Error")
    val userPassword: String,
    @field:NotNull(message = "userName cannot be null")
    val userName: String,
    @field:NotNull(message = "userNickname cannot be null")
    val userNickname: String
)