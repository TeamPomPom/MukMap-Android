package team.pompom.mukmap.model.init

import team.pompom.mukmap.model.base.BaseEntity

/**
 * 앱 초기 데이터 가져올 때 활용 되는 entity
 */
data class InitEntity(
    val appMinVersion: String,
    val appLatestVersion: String,
    val needToRefreshData: Boolean,
) : BaseEntity