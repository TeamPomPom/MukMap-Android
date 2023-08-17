package team.pompom.mukmap.entity.restaurant

import team.pompom.mukmap.base.BaseMapper
import team.pompom.mukmap.model.local.LocalRestaurantDataModel

object LocalRestaurantMapper : BaseMapper<LocalRestaurantDataModel, LocalRestaurantsEntity> {
    override fun toDataModel(data: LocalRestaurantsEntity): LocalRestaurantDataModel {
        return LocalRestaurantDataModel(
            id = data.restaurantId,
            name = data.name,
            lat = data.lat,
            lng = data.lng,
            fullAddress = data.fullAddress,
            youtubeThumbnail = data.youtubeThumbnail,
            youtubeVideoId = data.youtubeVideoId,
            naverPlaceId = data.naverPlaceId,
            mainCategory = data.mainCategory,
            subCategory = data.subCategory,
            episodeNum = data.episodeNum,
            province = data.province,
            district = data.district,
            oldDistrict = data.oldDistrict,
            youtubeTitle = data.youtubeTitle
        )
    }

    override fun toLocalEntity(data: LocalRestaurantDataModel): LocalRestaurantsEntity {
        return LocalRestaurantsEntity(
            restaurantId = data.id,
            name = data.name,
            lat = data.lat,
            lng = data.lng,
            fullAddress = data.fullAddress,
            youtubeThumbnail = data.youtubeThumbnail,
            youtubeVideoId = data.youtubeVideoId,
            naverPlaceId = data.naverPlaceId,
            mainCategory = data.mainCategory,
            subCategory = data.subCategory?.let { ArrayList(it) },
            episodeNum = data.episodeNum,
            province = data.province,
            district = data.district,
            oldDistrict = data.oldDistrict,
            youtubeTitle = data.youtubeTitle
        )
    }
}