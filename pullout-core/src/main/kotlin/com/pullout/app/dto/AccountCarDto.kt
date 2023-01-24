package com.pullout.app.dto

import com.pullout.app.domains.AccountCar
import java.time.LocalDateTime
import java.util.UUID

data class AccountCarDto(
    val id: UUID,
    val carNumber: String,
    val carType: String,
    val carNickName: String?,
    val createdAt: LocalDateTime,
    val createdBy: UUID,
    val deletedAt: LocalDateTime?,
    val deletedBy: UUID?,
    val carOwner: AccountUserDto
) {
    companion object {

        @JvmStatic
        fun ofEntity(entity: AccountCar) = AccountCarDto(
            id = entity.id,
            carNumber = entity.carNumber,
            carType = entity.carType,
            carNickName = entity.carNickname,
            createdAt = entity.createdAt,
            createdBy = entity.createdBy,
            deletedAt = entity.deletedAt,
            deletedBy = entity.deletedBy,
            carOwner = AccountUserDto.ofEntity(entity.carOwner)
        )
    }

}

