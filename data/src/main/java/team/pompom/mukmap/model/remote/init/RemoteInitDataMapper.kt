package team.pompom.mukmap.model.remote.init

import team.pompom.mukmap.base.BaseMapper
import team.pompom.mukmap.exception.init.InitException
import team.pompom.mukmap.model.init.InitEntity

/**
 * Init 데이터 변환 mapper
 */
object RemoteInitDataMapper : BaseMapper<RemoteInitDataModel, InitEntity> {
    override fun toEntity(data: RemoteInitDataModel): InitEntity {
        return InitEntity(
            appMinVersion = data.result?.minAppVersion ?: throw InitException.NoMinAppVersionException,
            appLatestVersion = data.result?.currAppVersion ?: throw InitException.NoCurrAppVersionException,
        )
    }
}