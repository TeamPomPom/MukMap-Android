package team.pompom.mukmap.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.entity.dataversion.LocalDataVersionEntity

@Dao
interface DataVersionDao {
    @Query("SELECT * FROM ${BaekDatabase.TABLE_DATA_VERSION} LIMIT 1")
    suspend fun getDataVersion(): LocalDataVersionEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDataVersion(localDataVersionEntity: LocalDataVersionEntity)

    @Query("DELETE FROM ${BaekDatabase.TABLE_DATA_VERSION}")
    suspend fun deleteAllDataVersion()
}