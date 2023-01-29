package com.pullout.app.services.impl

import com.pullout.app.domains.AccountUser
import com.pullout.app.domains.repositories.AccountUserRepository
import com.pullout.app.dto.AccountUserDto
import com.pullout.app.services.AuthUserService
import com.pullout.enums.UserRole
import mu.KLogging
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service


@Service
class AccountUserServiceImpl(
    private val userRepository: AccountUserRepository,
    private val passwordEncoder: PasswordEncoder
) : AuthUserService {
    companion object : KLogging()

    /**
     * 사용자 회원가입
     * @return 회원가입에 성공한 유저 정보
     */
    override fun registerUser(
        userId: String,
        userPassword: String,
        userName: String,
        userNickname: String
    ): AccountUserDto = AccountUserDto.ofEntity(
        userRepository.save(
            AccountUser(
                userId = this.getNotDuplicatedUserId(userId),
                userPassword = passwordEncoder.encode(userPassword),
                userNickname = this.getNotDuplicatedUserNickname(userNickname),
                userName = userName
            ).also { it.addUserRole(UserRole.USER) } // set user Role))
        )
    )


    /**
     * 중복되지 않은 사용자 아아디
     * @return 중복되지 않은 사용자 아이디
     * @exception DuplicateKeyException 중복된 사용자 아이디일 경우
     */
    private fun getNotDuplicatedUserId(userId: String): String = userRepository.findFirstByUserId(userId)?.let {
        throw DuplicateKeyException("userId is duplicated with id : $userId")
    } ?: userId

    /**
     * 중복되지 않은 사용자 닉네임
     * @return 중복되지 않은 사용자 닉네임
     * @exception DuplicateKeyException 중복된 사용자 닉네임이 있을 경우
     */
    private fun getNotDuplicatedUserNickname(userNickname: String) =
        userRepository.findFirstByUserNickname(userNickname)?.let {
            throw DuplicateKeyException("userNickname is duplicated with nickname : $userNickname")
        } ?: userNickname
}