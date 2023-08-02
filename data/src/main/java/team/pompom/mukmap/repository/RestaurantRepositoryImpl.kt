package team.pompom.mukmap.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import team.pompom.mukmap.extension.domainResultFlow
import team.pompom.mukmap.gateway.local.LocalRestaurantDataSource
import team.pompom.mukmap.gateway.remote.RemoteRestaurantDataSource
import team.pompom.mukmap.mapper.RestaurantDataMapper
import team.pompom.mukmap.model.base.DomainResultModel
import team.pompom.mukmap.model.restaurants.RestaurantsEntity
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val localRestaurantDataSource: LocalRestaurantDataSource,
    private val remoteRestaurantDataSource: RemoteRestaurantDataSource
) : RestaurantRepository {
    override fun getRestaurants(refreshRestaurant: Boolean, appName: String): Flow<DomainResultModel<RestaurantsEntity>> =
        domainResultFlow {
            if (refreshRestaurant) {
                remoteRestaurantDataSource.getRestaurants(appName).also {
                    localRestaurantDataSource.deleteAllRestaurants()
                    coroutineScope {
                        async(Dispatchers.IO) { localRestaurantDataSource.insertRestaurants(RestaurantDataMapper.listFromRemoteToLocal(it)) }
                    }
                }.run {
                    RestaurantDataMapper.fromRemoteToEntity(this)
                }
            } else {
                localRestaurantDataSource.getAllRestaurants().run {
                    RestaurantDataMapper.fromLocalToEntity(this)
                }
            }
        }

    override fun searchRestaurants(keyword: String): Flow<DomainResultModel<RestaurantsEntity>> = domainResultFlow {
        localRestaurantDataSource
            .searchRestaurants(keyword)
            .run { RestaurantDataMapper.fromLocalToEntity(this) }
    }
}