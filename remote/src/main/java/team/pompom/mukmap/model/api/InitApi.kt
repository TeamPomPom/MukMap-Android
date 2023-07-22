package team.pompom.mukmap.model.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import team.pompom.mukmap.model.remote.init.RemoteInitDataModel

interface InitApi {
    @GET("api/v1/configs/latest_version")
    suspend fun getLatestVersion(
        @Query("platform") platform: String = "android",
        @Query("app_name") appName: String
    ) : RemoteInitDataModel
}