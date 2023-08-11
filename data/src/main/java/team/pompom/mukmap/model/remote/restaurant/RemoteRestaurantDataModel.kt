package team.pompom.mukmap.model.remote.restaurant

import com.google.gson.annotations.SerializedName
import team.pompom.mukmap.base.BaseDataModel

data class RemoteRestaurantDataModel(
    @SerializedName("success") val success: Boolean? = null,
    @SerializedName("result") val result: Result? = Result(),
    @SerializedName("message") val message: String? = null
) : BaseDataModel {
    data class Result(
        @SerializedName("restaurants") val restaurants: List<Restaurant>? = null
    ) {
        data class Restaurant(
            @SerializedName("id") var id: Int? = null,
            @SerializedName("name") var name: String? = null,
            @SerializedName("lat") var lat: Double? = null,
            @SerializedName("lng") var lng: Double? = null,
            @SerializedName("full_address") var fullAddress: String? = null,
            @SerializedName("youtube_video") var youtubeVideo: YoutubeVideo? = null,
            @SerializedName("main_category") var mainCategory: String? = null,
            @SerializedName("sub_category") var subCategory: List<String>? = null,
            @SerializedName("province") var province: String? = null,
            @SerializedName("district") var district: String? = null,
            @SerializedName("old_district") var oldDistrict: String? = null,
        ) {
            data class YoutubeVideo(
                @SerializedName("youtube_url") var youtubeUrl: String? = null,
                @SerializedName("youtube_thumbnail") var youtubeThumbnail: String? = null,
                @SerializedName("naver_place_url") var naverPlaceUrl: String? = null,
                @SerializedName("episode_num") var episodeNum: Int? = null,
                @SerializedName("youtube_title") var youtubeTitle: String? = null,
            )
        }
    }
}
