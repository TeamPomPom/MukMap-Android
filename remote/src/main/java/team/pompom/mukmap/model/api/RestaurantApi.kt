package team.pompom.mukmap.model.api

import retrofit2.http.GET
import retrofit2.http.Query
import team.pompom.mukmap.model.remote.restaurant.RemoteRestaurantDataModel

interface RestaurantApi {
    @GET("api/v1/restaurants/app_restaurants")
    suspend fun getRestaurant(
        @Query("app_name") appName: String ,
        @Query("page_size") pageSize: Int = 100,
    ) : RemoteRestaurantDataModel
}