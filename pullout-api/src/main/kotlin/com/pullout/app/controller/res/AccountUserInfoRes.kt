package com.pullout.app.controller.res

import com.pullout.app.dto.AccountUserDto
import com.pullout.enums.UserRole
import com.pullout.enums.UserState
import java.util.*

data class AccountUserInfoRes(
    val id: UUID = UUID.randomUUID(),
    val userId: String,
    val userState: UserState?,
    val userRoleList: List<UserRole>?
) {

    companion object {

        fun ofDto(dto: AccountUserDto): AccountUserInfoRes {
            return AccountUserInfoRes(
                id = dto.id,
                userId = dto.userId,
                userState = dto.userState,
                userRoleList = dto.userRoleList
            )
        }

    }
}