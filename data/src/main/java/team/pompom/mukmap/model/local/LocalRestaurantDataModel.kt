package team.pompom.mukmap.model.local

import team.pompom.mukmap.base.BaseDataModel

data class LocalRestaurantDataModel(
    val id: Int?,
    val name: String?,
    val lat: String?,
    val lng: String?,
    val fullAddress: String?,
    val youtubeThumbnail: String?,
    val youtubeUrl: String?,
    val naverPlaceUrl: String?,
) : BaseDataModel