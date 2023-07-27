package team.pompom.mukmap.entity.restaurant

import androidx.room.Entity
import androidx.room.PrimaryKey
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.base.BaseLocalEntity

@Entity(tableName = BaekDatabase.TABLE_RESTAURANT)
data class LocalRestaurantsEntity(
    @PrimaryKey val restaurantId: Int?,
    val name: String?,
    val lat: String?,
    val lng: String?,
    val fullAddress: String?,
    val youtubeThumbnail: String?,
    val youtubeUrl: String?,
    val naverPlaceUrl: String?,
) : BaseLocalEntity