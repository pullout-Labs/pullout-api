package com.pullout.app.domains

import com.pullout.app.utils.DateTimeUtil
import com.pullout.objects.PrimaryKeyEntity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import javax.persistence.*

@Entity
@Table(name = "parking_info")
class ParkingInfo(
    carInfo: AccountCar,
    carInTime: LocalTime,
    carInDate: LocalDate? = DateTimeUtil.kstDateNow(),
    carOutTime: LocalTime,
    carOutDate: LocalDate? = DateTimeUtil.kstDateNow(),
    buildingId: AccountBuilding,
    createdBy: AccountUser,
    createdAt: LocalDateTime = DateTimeUtil.kstDateTimeNow()
) : PrimaryKeyEntity() {

    @Column(name = "car_in_time", nullable = false)
    var carInTime: LocalTime = carInTime
        protected set

    @Column(name = "car_in_date", nullable = false)
    var carInDate: LocalDate? = carInDate
        protected set

    @Column(name = "car_out_time", nullable = false)
    var carOutTime: LocalTime = carOutTime
        protected set

    @Column(name = "car_out_date", nullable = false)
    var carOutDate: LocalDate? = carOutDate
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "createdBy", nullable = false)
    var createdBy: AccountUser = createdBy
        protected set

    @Column(name = "createdAt", nullable = false)
    var createdAt: LocalDateTime = createdAt
        protected set

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "buildingId", nullable = false)
    var buildingId: AccountBuilding = buildingId
        protected set


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "car_info", nullable = false)
    var carInfo: AccountCar = carInfo
        protected set

}