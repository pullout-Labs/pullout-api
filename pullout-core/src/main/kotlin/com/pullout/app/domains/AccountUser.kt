package com.pullout.app.domains

import com.pullout.enums.UserRole
import com.pullout.enums.UserState
import com.pullout.objects.PrimaryKeyEntity
import java.util.NoSuchElementException
import javax.persistence.*

@Entity
@Table(name = "account_user")
class AccountUser(
    userId: String,
    userPassword : String,
    userNickname : String,
) : PrimaryKeyEntity() {

    /**
     * 사용자 아이디
     */
    @Column(name = "userId", nullable = false, unique = true)
    var userId: String = userId
        protected set

    /**
     * 사용자 비밀번호
     */
    @Column(name = "user_password", nullable = false)
    var userPassword : String = userPassword
        protected set

    /**
     * 사용자 닉네임
     */
    @Column(name = "user_nickname", nullable = false)
    var userNickname : String = userNickname
        protected set

    /**
     * 사용자 상태
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "user_state", nullable = false)
    var userState : UserState = UserState.ACTIVE
        protected set



    /**
     * 사용자 삭제
     */
    fun deletedUser() {
        if (userState == UserState.ACTIVE) {
            userState = UserState.DELETED
        }
    }

    /**
     * 사용자 권한 목록
     */
    @ElementCollection(fetch = FetchType.LAZY)
    protected val mutableRoles: MutableList<UserRole> = mutableListOf()
    val userRoles : List<UserRole> get() = mutableRoles.toList()

    /**
     * 권한 유무 체크
     */
    fun hasUserRole(userRole: UserRole): Boolean {
        return mutableRoles.contains(userRole)

    }

    /**
     * 사용자 권한 삭제
     */
    fun deleteUserRole(userRole: UserRole) {
        if (mutableRoles.contains(userRole)) {
            mutableRoles.remove(userRole)
        }
    }

    /**
     * 사용자 권한 추가
     */
    fun addUserRole(userRole: UserRole) {
        if (!mutableRoles.contains(userRole)) {
            mutableRoles.add(userRole)
        }
    }

    /**
     * 보유한 자동차 목록
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "carOwner")
    protected val mutableCars: MutableList<AccountCar> = mutableListOf()
    val carList: List<AccountCar> get() = mutableCars.toList()


    /**
     * 자동차 추가
     */
    fun registerCar(accountCar: AccountCar) {
        if (!mutableCars.contains(accountCar)) {
            mutableCars.add(accountCar)
        }
    }

    /**
     * 자동차 삭제
     */
    fun removeCar(targetCar: AccountCar) = if(mutableCars.contains(targetCar)) {
        mutableCars.remove(targetCar)
    } else {
        throw NoSuchElementException("cannot found car with car : " + targetCar.id)
    }


}