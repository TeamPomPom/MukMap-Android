package team.pompom.mukmap.datasource

import team.pompom.mukmap.gateway.remote.RemoteInitDataSource
import team.pompom.mukmap.model.api.InitApi
import team.pompom.mukmap.model.remote.init.RemoteInitDataModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteInitDataSourceImpl @Inject constructor(
    private val initApi: InitApi
) : RemoteInitDataSource {
    override suspend fun getInitData(appName: String): RemoteInitDataModel = initApi.getLatestVersion(
        platform = "android",
        appName = appName
    )
}