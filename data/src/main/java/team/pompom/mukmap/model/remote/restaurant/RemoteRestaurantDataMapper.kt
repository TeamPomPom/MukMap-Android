package team.pompom.mukmap.model.remote.restaurant

import team.pompom.mukmap.base.BaseMapper
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

object RemoteRestaurantDataMapper : BaseMapper<RemoteRestaurantDataModel, RestaurantsEntity> {
    override fun toEntity(data: RemoteRestaurantDataModel): RestaurantsEntity {
        return RestaurantsEntity(
            restaurants = data.result?.restaurants?.map {
                RestaurantsEntity.Restaurant(
                    id = it.id,
                    name = it.name,
                    lat = it.lat,
                    lng = it.lng,
                    fullAddress = it.fullAddress,
                    youtubeThumbnail = it.youtubeVideo?.youtubeThumbnail,
                    youtubeUrl = it.youtubeVideo?.youtubeUrl,
                    naverPlaceUrl = it.youtubeVideo?.naverPlaceUrl,
                )
            } ?: listOf()
        )
    }
}