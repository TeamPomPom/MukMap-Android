package team.pompom.mukmap.entity.dataversion

import team.pompom.mukmap.base.BaseMapper
import team.pompom.mukmap.model.local.LocalDataVersionDataModel

object LocalDataVersionMapper : BaseMapper<LocalDataVersionDataModel, LocalDataVersionEntity> {
    override fun toDataModel(data: LocalDataVersionEntity): LocalDataVersionDataModel {
        return LocalDataVersionDataModel(dataVersion = data.dataVersion)
    }

    override fun toLocalEntity(data: LocalDataVersionDataModel): LocalDataVersionEntity {
        return LocalDataVersionEntity(data.dataVersion)
    }
}