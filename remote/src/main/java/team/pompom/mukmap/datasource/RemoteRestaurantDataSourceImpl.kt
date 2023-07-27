package team.pompom.mukmap.datasource

import android.util.Log
import team.pompom.mukmap.gateway.remote.RemoteRestaurantDataSource
import team.pompom.mukmap.model.api.RestaurantApi
import team.pompom.mukmap.model.remote.restaurant.RemoteRestaurantDataModel
import javax.inject.Inject

class RemoteRestaurantDataSourceImpl @Inject constructor(
    private val restaurantApi: RestaurantApi
) : RemoteRestaurantDataSource {
    override suspend fun getRestaurants(appName: String): RemoteRestaurantDataModel {
        Log.d("Ram Test", "getRestaurants")
        return restaurantApi.getRestaurant(appName = appName)
    }
}