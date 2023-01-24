package com.pullout.app.domains

import com.pullout.objects.PrimaryKeyEntity
import java.time.LocalDateTime
import java.util.UUID
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import javax.persistence.Table

@Entity
@Table(name = "account_car")
class AccountCar(
    carNumber : String,
    carType : String,
    carNickname : String? = "myCar",
    createdAt : LocalDateTime,
    createdBy : UUID,
    deletedAt : LocalDateTime? = null,
    deletedBy : UUID? = null,
    carOwner : AccountUser
) : PrimaryKeyEntity() {

    /**
     * 자동차 번호
     */
    @Column(nullable = false)
    var carNumber : String = carNumber
        protected set

    /**
     * 자동차 종류
     */
    @Column(nullable = false)
    var carType : String = carType
        protected set

    /**
     * 사용자 설정 자동차 Nickname
     */
    @Column(nullable = false)
    var carNickname : String? = carNickname
        protected set

    /**
     * 생성일
     */
    @Column(nullable = false)
    var createdAt : LocalDateTime = createdAt
        protected set

    /**
     * 생성자
     */
    @Column(nullable = false)
    var createdBy : UUID = createdBy
        protected set

    /**
     * 삭제일
     */
    @Column(nullable = false)
    var deletedAt : LocalDateTime? = deletedAt
        protected set

    /**
     * 삭제자
     */
    @Column(nullable = false)
    var deletedBy : UUID? = deletedBy
        protected set

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "carInfo")
    protected var mutableParkingInfos: MutableList<ParkingInfo> = mutableListOf()
    val parkingInfos: List<ParkingInfo> get() = mutableParkingInfos.toList()



    /**
     * 소유자
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(nullable = false)
    var carOwner: AccountUser = carOwner
        protected set

}