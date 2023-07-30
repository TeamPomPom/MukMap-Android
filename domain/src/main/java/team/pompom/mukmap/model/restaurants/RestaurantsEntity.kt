package team.pompom.mukmap.model.restaurants

import team.pompom.mukmap.model.base.BaseEntity

data class RestaurantsEntity(
    val restaurants: List<Restaurant> = arrayListOf()
) : BaseEntity {
    data class Restaurant(
        val id: Int?,
        val name: String?,
        val lat: String?,
        val lng: String?,
        val fullAddress: String?,
        val youtubeThumbnail: String?,
        val youtubeUrl: String?,
        val naverPlaceUrl: String?,
        val mainCategory: String?,
        val subCategory: List<String>?,
        val episodeNum: Int?,
        val province: String?,
        val district: String?,
        val oldDistrict: String?,
    )
}