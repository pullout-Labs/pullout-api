package com.pullout.app.domains.repositories

import com.pullout.app.domains.AccountCar
import com.pullout.objects.PrimaryKeyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountCarRepository : JpaRepository<AccountCar, PrimaryKeyEntity> {
}