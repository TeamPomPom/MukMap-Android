package team.pompom.mukmap.base

import team.pompom.mukmap.model.base.BaseEntity

/**
 * DataLayer -> DomainLayer 로 데이터를 변환해 주도록 하는 interface
 */
interface BaseMapper<D: BaseDataModel, E: BaseEntity> {
    fun toEntity(data: D) : E
}