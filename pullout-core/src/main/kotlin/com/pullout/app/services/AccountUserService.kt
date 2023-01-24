package com.pullout.app.services

import com.pullout.app.dto.AccountUserDto

/**
 * core 외부에서 호출하는 서비스의 경우 interface 를 사용
 */
interface AccountUserService {

    fun findAllUserList(): List<AccountUserDto>



}