package team.pompom.mukmap.gateway.local

import team.pompom.mukmap.model.local.LocalRestaurantDataModel

interface LocalRestaurantDataSource {
    suspend fun getAllRestaurants(): List<LocalRestaurantDataModel>
    suspend fun deleteAllRestaurants()
    suspend fun insertRestaurants(restaurants: List<LocalRestaurantDataModel>)
}