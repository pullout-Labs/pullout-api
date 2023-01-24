package com.pullout.app.domains.repositories

import com.pullout.app.domains.ParkingInfo
import com.pullout.objects.PrimaryKeyEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ParkingInfoRepository : JpaRepository<ParkingInfo, PrimaryKeyEntity> {
}