package team.pompom.mukmap.mapper

import team.pompom.mukmap.model.local.LocalRestaurantDataModel
import team.pompom.mukmap.model.remote.restaurant.RemoteRestaurantDataModel
import team.pompom.mukmap.model.restaurants.RestaurantsEntity

object RestaurantDataMapper {
    fun fromRemoteToEntity(data: RemoteRestaurantDataModel): RestaurantsEntity {
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

    fun listFromRemoteToLocal(remoteData: RemoteRestaurantDataModel): List<LocalRestaurantDataModel> {
        return remoteData.result?.restaurants?.map {
            LocalRestaurantDataModel(
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
    }

    fun fromLocalToEntity(localRestaurantDataModel: List<LocalRestaurantDataModel>): RestaurantsEntity {
        return RestaurantsEntity(
            restaurants = localRestaurantDataModel.map {
                RestaurantsEntity.Restaurant(
                    id = it.id,
                    name = it.name,
                    lat = it.lat,
                    lng = it.lng,
                    fullAddress = it.fullAddress,
                    youtubeThumbnail = it.youtubeThumbnail,
                    youtubeUrl = it.youtubeUrl,
                    naverPlaceUrl = it.naverPlaceUrl,
                )
            }
        )
    }
}