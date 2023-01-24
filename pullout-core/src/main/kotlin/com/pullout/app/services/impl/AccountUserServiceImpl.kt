package com.pullout.app.services.impl

import com.pullout.app.domains.repositories.AccountUserRepository
import com.pullout.app.dto.AccountUserDto
import com.pullout.app.services.AccountUserService
import mu.KLogging
import org.springframework.stereotype.Service
import kotlin.streams.toList


@Service
class AccountUserServiceImpl(val accountUserRepository: AccountUserRepository) : AccountUserService {
    companion object : KLogging()

    override fun findAllUserList(): List<AccountUserDto> =
        accountUserRepository.findAll().stream().map { AccountUserDto.ofEntity(it) }.toList()

}