package team.pompom.mukmap.datasource

import android.util.Log
import team.pompom.mukmap.dao.RestaurantDao
import team.pompom.mukmap.entity.restaurant.LocalRestaurantMapper
import team.pompom.mukmap.gateway.local.LocalRestaurantDataSource
import team.pompom.mukmap.model.local.LocalRestaurantDataModel
import javax.inject.Inject

class RestaurantDataSourceImpl @Inject constructor(
    private val restaurantDao: RestaurantDao
) : LocalRestaurantDataSource {
    override suspend fun getAllRestaurants(): List<LocalRestaurantDataModel> {
        Log.d("Ram Test", "getAllRestaurants")
        return restaurantDao
            .getAllRestaurants()
            .map { LocalRestaurantMapper.toDataModel(it) }
    }

    override suspend fun deleteAllRestaurants() {
        Log.d("Ram Test", "deleteAllRestaurants")
        restaurantDao.deleteAllRestaurants()
    }

    override suspend fun insertRestaurants(restaurants: List<LocalRestaurantDataModel>) {
        Log.d("Ram Test", "insertRestaurants")
        restaurantDao
            .insertRestaurants(
                restaurants = restaurants
                    .map { LocalRestaurantMapper.toLocalEntity(it) }
            )
    }

    override suspend fun searchRestaurants(keyword: String): List<LocalRestaurantDataModel> {
        Log.d("Ram Test", "searchRestaurants")
        return restaurantDao
            .searchRestaurants(keyword)
            .map { LocalRestaurantMapper.toDataModel(it) }
    }
}