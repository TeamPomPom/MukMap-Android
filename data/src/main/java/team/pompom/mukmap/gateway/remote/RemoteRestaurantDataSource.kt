package team.pompom.mukmap.gateway.remote

import team.pompom.mukmap.model.remote.restaurant.RemoteRestaurantDataModel

interface RemoteRestaurantDataSource {
    suspend fun getRestaurants(appName: String) : RemoteRestaurantDataModel
}