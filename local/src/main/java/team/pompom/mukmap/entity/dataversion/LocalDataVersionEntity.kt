package team.pompom.mukmap.entity.dataversion

import androidx.room.Entity
import androidx.room.PrimaryKey
import team.pompom.mukmap.BaekDatabase
import team.pompom.mukmap.base.BaseLocalEntity

@Entity(tableName = BaekDatabase.TABLE_DATA_VERSION)
data class LocalDataVersionEntity(
    @PrimaryKey val dataVersion: Int,
) : BaseLocalEntity
