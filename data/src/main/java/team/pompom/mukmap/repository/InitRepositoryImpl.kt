package team.pompom.mukmap.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.extension.domainResultFlow
import team.pompom.mukmap.gateway.local.LocalDataVersionDataSource
import team.pompom.mukmap.gateway.remote.RemoteInitDataSource
import team.pompom.mukmap.mapper.InitDataMapper
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.init.InitEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * init 데이터 repository 구현체
 */
@Singleton
class InitRepositoryImpl @Inject constructor(
    private val localDataVersionDataSource: LocalDataVersionDataSource,
    private val remoteInitDataSource: RemoteInitDataSource
) : InitRepository {
    override fun getInitData(appName: String): Flow<DomainResultModel<InitEntity>> =
        domainResultFlow {
            val cachedDataVersion = localDataVersionDataSource.getDataVersion()
            val remoteInitData = remoteInitDataSource.getInitData(appName)
            InitDataMapper.toEntity(remoteInitData, cachedDataVersion).also {
                if (cachedDataVersion?.dataVersion != remoteInitData.result?.dataVersion) {
                    localDataVersionDataSource.deleteAllDataVersion()
                    coroutineScope {
                        async(Dispatchers.IO) { localDataVersionDataSource.insertDataVersion(InitDataMapper.fromRemoteToLocal(remoteInitData)) }
                    }
                }
            }
        }
}