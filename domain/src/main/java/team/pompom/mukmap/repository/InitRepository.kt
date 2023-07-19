package team.pompom.mukmap.repository

import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.init.InitEntity

/**
 * 앱 초기화 시점에 활용되는 데이터들에 대한 Repository
 */
interface InitRepository {
    fun getInitData(appName: String) : Flow<DomainResultModel<InitEntity>>
}