package team.pompom.mukmap.mapper

import team.pompom.mukmap.exception.init.InitException
import team.pompom.mukmap.model.init.InitEntity
import team.pompom.mukmap.model.local.LocalDataVersionDataModel
import team.pompom.mukmap.model.remote.init.RemoteInitDataModel

object InitDataMapper {
    fun toEntity(remoteData: RemoteInitDataModel?, localData: LocalDataVersionDataModel?): InitEntity {
        return InitEntity(
            appMinVersion = remoteData?.result?.minAppVersion ?: throw InitException.NoMinAppVersionException,
            appLatestVersion = remoteData.result.currAppVersion ?: throw InitException.NoCurrAppVersionException,
            needToRefreshData = remoteData.result.dataVersion != localData?.dataVersion
        )
    }

    fun fromRemoteToLocal(remoteData: RemoteInitDataModel) : LocalDataVersionDataModel {
        return LocalDataVersionDataModel(
            dataVersion = remoteData.result?.dataVersion ?: 0
        )
    }
}