package com.pullout.app.domains

import com.pullout.objects.PrimaryKeyEntity
import javax.persistence.*

@Entity
@Table(name = "account_building")
class AccountBuilding(
    buildingName: String,
    buildingAddress: String,
    buildingAddressDetails: String?,
    parkingSpaceCount: Int = 0
) : PrimaryKeyEntity() {

    /**
     * 건물 이름
     */
    @Column(name = "building_name", nullable = false)
    var buildingName: String = buildingName
        protected set

    /**
     * 건물 주소
     */
    @Column(name = "building_address", nullable = false)
    var buildingAddress: String = buildingAddress
        protected set

    /**
     * 건물 주소 상세
     */
    @Column(name = "building_address_details")
    var buildingAddressDetails: String? = buildingAddressDetails
        protected set

    /**
     * 건물 주차 가능 공간 수
     */
    @Column(name = "parking_space_count", nullable = false)
    var parkingSpaceCount: Int = parkingSpaceCount
        protected set


    /**
     * 건물에 주차된 자동차 목록
     */
    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], mappedBy = "buildingId")
    protected val mutableParkingInfo: MutableList<ParkingInfo> = mutableListOf()
    val parkingInfoList : List<ParkingInfo> get() = mutableParkingInfo.toList()




}