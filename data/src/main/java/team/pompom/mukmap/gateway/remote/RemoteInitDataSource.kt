package team.pompom.mukmap.gateway.remote

import team.pompom.mukmap.model.remote.init.RemoteInitDataModel

/**
 * 네트워크 통해서 init 데이터 가져오기 위한 dataSource
 */
interface RemoteInitDataSource {
    suspend fun getInitData(appName: String): RemoteInitDataModel
}