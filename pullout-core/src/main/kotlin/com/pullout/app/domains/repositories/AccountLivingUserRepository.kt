package com.pullout.app.domains.repositories

import com.pullout.app.domains.AccountLivingUser
import com.pullout.objects.PrimaryKeyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountLivingUserRepository : JpaRepository<AccountLivingUser, PrimaryKeyEntity> {
}