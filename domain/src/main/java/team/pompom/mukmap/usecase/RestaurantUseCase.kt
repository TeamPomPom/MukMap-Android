package team.pompom.mukmap.usecase

import team.pompom.mukmap.repository.RestaurantRepository
import javax.inject.Inject

/**
 * 음식점 정보에 대한 UseCase
 */
class RestaurantUseCase @Inject constructor(
    private val restaurantRepository : RestaurantRepository
) {
    fun getRestaurants(appName: String) = restaurantRepository.getRestaurants(appName)
}