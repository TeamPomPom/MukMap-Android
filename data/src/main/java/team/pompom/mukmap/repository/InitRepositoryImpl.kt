package team.pompom.mukmap.repository

import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.base.domainResultFlow
import team.pompom.mukmap.gateway.remote.RemoteInitDataSource
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.init.InitEntity
import team.pompom.mukmap.model.remote.init.RemoteInitDataMapper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * init 데이터 repository 구현체
 */
@Singleton
class InitRepositoryImpl @Inject constructor(
    private val remoteInitDataSource: RemoteInitDataSource
) : InitRepository {
    override fun getInitData(appName: String): Flow<DomainResultModel<InitEntity>> =
        domainResultFlow(RemoteInitDataMapper) { remoteInitDataSource.getInitData(appName) }
}