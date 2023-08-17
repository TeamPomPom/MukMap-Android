package team.pompom.mukmap.model.restaurants

import team.pompom.mukmap.model.base.BaseEntity
import java.io.Serializable

data class RestaurantsEntity(
    val restaurants: List<Restaurant> = arrayListOf()
) : BaseEntity {
    data class Restaurant(
        val id: Int?,
        val name: String?,
        val lat: Double?,
        val lng: Double?,
        val fullAddress: String?,
        val youtubeTitle: String?,
        val youtubeThumbnail: String?,
        val youtubeVideoId: String?,
        val naverPlaceId: String?,
        val mainCategory: String?,
        val subCategory: List<String>?,
        val episodeNum: Int?,
        val province: String?,
        val district: String?,
        val oldDistrict: String?,
    ): Serializable
}