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
            youtubeUrl = data.youtubeUrl,
            naverPlaceUrl = data.naverPlaceUrl,
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
            youtubeUrl = data.youtubeUrl,
            naverPlaceUrl = data.naverPlaceUrl,
        )
    }
}