package team.pompom.mukmap.base

interface BaseMapper<D : BaseDataModel, E : BaseLocalEntity> {
    fun toDataModel(data: E): D
    fun toLocalEntity(data: D): E
}