package team.pompom.mukmap.model.remote.init

import com.google.gson.annotations.SerializedName
import team.pompom.mukmap.base.BaseDataModel
import java.io.Serializable

/**
 * InitData 모델 클래스
 */
data class RemoteInitDataModel(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("result") val result: Result? = Result(),
    @SerializedName("message") val message: String? = null
) : BaseDataModel {
    data class Result(
        @SerializedName("curr_app_version") val currAppVersion: String? = null,
        @SerializedName("min_app_version") val minAppVersion: String? = null,
        @SerializedName("data_version") val dataVersion: Int? = null,
    )
}