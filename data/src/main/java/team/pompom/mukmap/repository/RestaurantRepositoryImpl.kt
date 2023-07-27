package team.pompom.mukmap.repository

import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.base.domainResultFlow
import team.pompom.mukmap.gateway.remote.RemoteRestaurantDataSource
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.remote.restaurant.RemoteRestaurantDataMapper
import team.pompom.mukmap.model.restaurants.RestaurantsEntity
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val remoteRestaurantDataSource: RemoteRestaurantDataSource
) : RestaurantRepository {
    override fun getRestaurants(appName: String): Flow<DomainResultModel<RestaurantsEntity>>
            = domainResultFlow(RemoteRestaurantDataMapper) { remoteRestaurantDataSource.getRestaurants(appName) }
}