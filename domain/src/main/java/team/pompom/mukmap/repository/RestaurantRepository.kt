package team.pompom.mukmap.repository

import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

/**
 * 음식점 정보에 대한 UseCase
 */
interface RestaurantRepository {
    fun getRestaurants(refreshRestaurant: Boolean, appName: String) : Flow<DomainResultModel<RestaurantsEntity>>
    fun searchRestaurants(keyword: String) : Flow<DomainResultModel<RestaurantsEntity>>
}