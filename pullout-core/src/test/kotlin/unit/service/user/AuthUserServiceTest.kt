package service.user

import com.pullout.app.domains.AccountUser
import com.pullout.app.domains.repositories.AccountUserRepository
import com.pullout.app.services.AuthUserService
import com.pullout.app.services.impl.AccountUserServiceImpl
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.dao.DuplicateKeyException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import java.lang.Exception
import javax.swing.text.BadLocationException


internal class AuthUserServiceTest {
    private val accountUserRepositoryMock: AccountUserRepository = mockk()
    private val passwordEncoderMock: PasswordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    private val target: AuthUserService = AccountUserServiceImpl(
        userRepository = accountUserRepositoryMock,
        passwordEncoder = passwordEncoderMock
    )

    private val userHasDuplicatedId: AccountUser = AccountUser(
        userId = "duplicatedUserId",
        userPassword = "encodedPassword",
        userNickname = "userNickname",
        userName = "userName"
    )

    private val userHasDuplicatedUserNickname: AccountUser = AccountUser(
        userId = "userId",
        userPassword = "encodedPassword",
        userNickname = "duplicatedUserNickname",
        userName = "userName"
    )

    private val saveUserResult: AccountUser = AccountUser(
        userId = "userId",
        userPassword = "encodedPassword",
        userNickname = "userNickname",
        userName = "userName"
    )

    @Test
    fun `회원가입 시 아이디, 닉네임 중복 확인 로직 실행`() {
        // given
        every { accountUserRepositoryMock.findFirstByUserId(any()) } returns null // 사용 가능한 아이디
        every { accountUserRepositoryMock.findFirstByUserNickname(any()) } returns null // 사용 가능한 닉네임
        every { accountUserRepositoryMock.save(any()) } returns saveUserResult
        // when
        var result = target.registerUser(
            userId = "userId",
            userPassword = "userPassword",
            userName = "userName",
            userNickname = "userNickname"
        )

        // then
        verify(exactly = 1) { accountUserRepositoryMock.findFirstByUserId(any()) } // 아이디 중복 조회 실행
        verify(exactly = 1) { accountUserRepositoryMock.findFirstByUserNickname(any()) } // 닉네임 중복 조회 실행
    }

    @Test
    fun `중복된 아이디를 사용하여 회원가입`() {
        // given
        every { accountUserRepositoryMock.findFirstByUserId("duplicatedUserId") } returns userHasDuplicatedId
        every { accountUserRepositoryMock.findFirstByUserNickname(any()) } returns null // 사용 가능한 닉네임

        // when
        val exception = assertThrows<Exception> {
            target.registerUser(
                userId = "duplicatedUserId",
                userPassword = "userPassword",
                userName = "userName",
                userNickname = "userNickname"
            )
        }

        //then
        verify(exactly = 0) { accountUserRepositoryMock.save(any()) } // 회원가입 X
        Assertions.assertEquals(exception::class, DuplicateKeyException::class)
    }


    @Test
    fun `중복된 닉네임을 사용하여 회원가입`() {
        // given
        every { accountUserRepositoryMock.findFirstByUserId(any()) } returns null // 사용 가능한 아이디
        every { accountUserRepositoryMock.findFirstByUserNickname("duplicatedUserNickname") } returns userHasDuplicatedUserNickname // 사용 가능한 닉네임

        // when
        val exception = assertThrows<Exception> {
            target.registerUser(
                userId = "userId",
                userPassword = "userPassword",
                userName = "userName",
                userNickname = "duplicatedUserNickname"
            )
        }

        //then
        verify(exactly = 0) { accountUserRepositoryMock.save(any()) } // 회원가입 X
        Assertions.assertEquals(exception::class, DuplicateKeyException::class)
        Assertions.assertEquals(exception.message, "userNickname is duplicated with nickname : ${userHasDuplicatedUserNickname.userNickname}")
    }
}