package team.pompom.mukmap.model.remote.init

import team.pompom.mukmap.base.BaseDataModel

/**
 * InitData 모델 클래스
 */
data class RemoteInitDataModel(
    val currVersion: String,
    val minVersion: String
) : BaseDataModel
