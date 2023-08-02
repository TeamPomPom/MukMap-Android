package team.pompom.mukmap.usecase

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import team.pompom.mukmap.model.restaurants.RestaurantsEntity
import team.pompom.mukmap.repository.RestaurantRepository
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 음식점 정보에 대한 UseCase
 */
@Singleton
class RestaurantUseCase @Inject constructor(
    private val restaurantRepository : RestaurantRepository
) {

    private val _cachedRestaurant = MutableStateFlow<List<RestaurantsEntity.Restaurant>>(listOf())
    val cachedRestaurant = _cachedRestaurant.asStateFlow()

    fun getRestaurants(refreshRestaurant: Boolean, appName: String) = restaurantRepository.getRestaurants(refreshRestaurant, appName)

    fun cachingRestaurants(restaurants: List<RestaurantsEntity.Restaurant>) {
        _cachedRestaurant.value = restaurants
    }

    fun searchRestaurants(keyword: String) = restaurantRepository.searchRestaurants(keyword)
}