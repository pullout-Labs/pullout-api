package com.pullout.app.domains

import com.pullout.app.utils.DateTimeUtil
import com.pullout.enums.LivingUserRole
import com.pullout.objects.PrimaryKeyEntity
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.*

@Entity
@Table(name = "account_living_user")
class AccountLivingUser(
    livingUserRole: LivingUserRole,
    createdBy: UUID,
    createdAt: LocalDateTime = DateTimeUtil.kstDateTimeNow(),
    deletedBy: UUID?,
    deletedAt: LocalDateTime?,
    userId: AccountUser,
    buildingId: AccountBuilding
) : PrimaryKeyEntity() {

    /**
     * 사용자 건물 Role
     */
    @Column(name = "living_user_role", nullable = false)
    var livingUserRole: LivingUserRole = livingUserRole
        protected set

    /**
     * 사용자 권한 변경
     */
    fun changeUserRole(newUserRole: LivingUserRole) = newUserRole.also {
        livingUserRole = it
    }

    /**
     * 생성자 아이디
     */
    @Column(name = "created_by", nullable = false)
    var createdBy: UUID = createdBy
        protected set

    /**
     * 생성 시간
     */
    @Column(name = "created_at", nullable = false)
    var createdAt: LocalDateTime = createdAt
        protected set

    /**
     * 삭제자 아이디
     */
    @Column(name = "deleted_by")
    var deletedBy: UUID? = deletedBy
        protected set

    /**
     * 삭제일
     */
    @Column(name = "deleted_at")
    var deletedAt: LocalDateTime? = deletedAt
        protected set

    /**
     * 사용자 아이디
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    var userId: AccountUser = userId
        protected set

    /**
     * 건물 번호
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    var buildingId : AccountBuilding = buildingId
        protected set


}