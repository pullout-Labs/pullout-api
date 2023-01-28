package com.pullout.app.services

import com.pullout.app.dto.AccountUserDto

/**
 * 사용자 인증 처리 서비스 interface
 * 회원가입, 로그인
 */
interface AuthUserService {

    /**
     * 사용자 회원가입
     * @return 회원가입에 성공한 유저 아이디
     */
    fun registerUser(userId: String, userPassword: String, userName: String, userNickname: String): AccountUserDto
}