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
                    mainCategory = it.mainCategory,
                    subCategory = it.subCategory,
                    youtubeThumbnail = it.youtubeVideo?.youtubeThumbnail,
                    youtubeUrl = it.youtubeVideo?.youtubeUrl,
                    naverPlaceId = it.youtubeVideo?.naverPlaceId,
                    episodeNum = it.youtubeVideo?.episodeNum,
                    province = it.province,
                    district = it.district,
                    oldDistrict = it.oldDistrict,
                    youtubeTitle = it.youtubeVideo?.youtubeTitle
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
                naverPlaceId = it.youtubeVideo?.naverPlaceId,
                mainCategory = it.mainCategory,
                subCategory = it.subCategory,
                episodeNum = it.youtubeVideo?.episodeNum,
                province = it.province,
                district = it.district,
                oldDistrict = it.oldDistrict,
                youtubeTitle = it.youtubeVideo?.youtubeTitle
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
                    naverPlaceId = it.naverPlaceId,
                    mainCategory = it.mainCategory,
                    subCategory = it.subCategory,
                    episodeNum = it.episodeNum,
                    province = it.province,
                    district = it.district,
                    oldDistrict = it.oldDistrict,
                    youtubeTitle = it.youtubeTitle
                )
            }
        )
    }
}