package com.pullout.app.controller.req

import javax.validation.constraints.NotNull

data class RegisterUserReq(
    @field:NotNull(message = "userId cannot be null") val userId: String,
    @field:NotNull(message = "userPassword cannot be null") val userPassword: String,
    @field:NotNull(message = "userName cannot be null") val userName: String,
    @field:NotNull(message = "userNickname cannot be null") val userNickname: String
)