package team.pompom.mukmap

import androidx.room.Database
import androidx.room.RoomDatabase
import team.pompom.mukmap.dao.DataVersionDao
import team.pompom.mukmap.dao.RestaurantDao
import team.pompom.mukmap.entity.dataversion.LocalDataVersionEntity
import team.pompom.mukmap.entity.restaurant.LocalRestaurantsEntity

@Database(
    entities = [
        LocalRestaurantsEntity::class,
        LocalDataVersionEntity::class
    ], version = 1
)
abstract class BaekDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "baek_db"
        const val TABLE_RESTAURANT = "restaurant"
        const val TABLE_DATA_VERSION = "data_version"
    }

    abstract fun restaurantDao(): RestaurantDao
    abstract fun dataVersionDao(): DataVersionDao
}