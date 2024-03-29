package team.pompom.mukmap.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.entity.restaurant.LocalRestaurantsEntity

@Dao
interface RestaurantDao {
    @Query("SELECT * FROM ${BaekDatabase.TABLE_RESTAURANT}")
    suspend fun getAllRestaurants(): List<LocalRestaurantsEntity>

    @Query("DELETE FROM ${BaekDatabase.TABLE_RESTAURANT}")
    suspend fun deleteAllRestaurants()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRestaurants(restaurants: List<LocalRestaurantsEntity>)

    @Query(
        "SELECT * FROM ${BaekDatabase.TABLE_RESTAURANT} " +
                "WHERE name LIKE '%' || :keyword || '%' " +
                "OR fullAddress LIKE '%' || :keyword || '%' " +
                "OR oldDistrict LIKE '%' || :keyword || '%' " +
                "OR mainCategory LIKE '%' || :keyword || '%' " +
                "OR subCategory LIKE '%' || :keyword || '%' " +
                "OR episodeNumText LIKE '%' || :keyword || '%' "
    )
    suspend fun searchRestaurants(keyword: String): List<LocalRestaurantsEntity>
}