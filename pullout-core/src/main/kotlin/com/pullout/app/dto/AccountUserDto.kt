package com.pullout.app.dto

import com.pullout.app.domains.AccountUser
import com.pullout.enums.UserRole
import com.pullout.enums.UserState
import java.util.*
import kotlin.streams.toList

data class AccountUserDto(
    val id: UUID,
    val userId: String,
    val userPassword: String,
    val userState: UserState,
    val userNickname: String,
    val userName: String,
    val userCarList: List<AccountCarDto> = listOf(),
    val userRoleList: List<UserRole> = listOf()
) {

    companion object {

        @JvmStatic
        fun ofEntity(entity: AccountUser): AccountUserDto {
            return AccountUserDto(
                id = entity.id,
                userId = entity.userId,
                userPassword = entity.userPassword,
                userNickname = entity.userNickname,
                userName = entity.userName,
                userState = entity.userState,
                userCarList = entity.carList.stream().map { AccountCarDto.ofEntity(it) }.toList(),
                userRoleList = entity.userRoles
            )
        }
    }
}
