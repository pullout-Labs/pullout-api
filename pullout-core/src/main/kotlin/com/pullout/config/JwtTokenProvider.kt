package com.pullout.config

import com.pullout.app.domains.AccountUser
import com.pullout.enums.UserRole
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct

@Component
class JwtTokenProvider(
    private val userDetailsService: UserDetailsService
) {
    private var secretKey = "sdfewfo"

    // 객체 초기화
    @PostConstruct
    protected fun init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.toByteArray())
    }

    // JWT 토큰 생성
    private fun createToken(params: Map<String, Any>): String {

        val headers: Map<String, Any> =
            hashMapOf("typ" to "JWT", "alg" to "HS256")

        val payloads = hashMapOf<String, Any>()
        for ((key, value) in params) {
            payloads[key.toString()] = value.toString()
        }

        val expiration = params["exp"] as Date

        return Jwts.builder()
            .setHeader(headers)
            .setClaims(payloads)
            .setExpiration(expiration)
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    // 액세스 토큰 생성
    fun createAccessToken(user: AccountUser): String {

        val expiredAt = Date.from(LocalDateTime.now().plusDays(1).atZone(ZoneId.systemDefault()).toInstant())

        val jwtParams: Map<String, Any> =
            hashMapOf("id" to user.userId, "exp" to expiredAt)

        return setRole(user, jwtParams as MutableMap<String, Any>)
    }

    // 리프레쉬 토큰 생성
    fun createRefreshToken(user: AccountUser): String {

        val expiredAt = Date.from(LocalDateTime.now().plusDays(30).atZone(ZoneId.systemDefault()).toInstant())

        val jwtParams: Map<String, Any> =
            hashMapOf("id" to user.userId, "exp" to expiredAt)

        return setRole(user, jwtParams as MutableMap<String, Any>)
    }

    // 권한 셋팅
    private fun setRole(user: AccountUser, jwtParams: MutableMap<String, Any>): String {

        val roles = user.userRoles

        if (roles.contains(UserRole.USER)) {
            jwtParams["user"] = true
            jwtParams["admin"] = false
        }

        if (roles.contains(UserRole.ADMIN)) {
            jwtParams["user"] = false
            jwtParams["admin"] = true
        }

        return createToken(jwtParams)
    }

}