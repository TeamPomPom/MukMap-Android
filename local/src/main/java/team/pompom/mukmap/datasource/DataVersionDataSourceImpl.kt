package team.pompom.mukmap.datasource

import android.util.Log
import team.pompom.mukmap.dao.DataVersionDao
import team.pompom.mukmap.entity.dataversion.LocalDataVersionMapper
import team.pompom.mukmap.gateway.local.LocalDataVersionDataSource
import team.pompom.mukmap.model.local.LocalDataVersionDataModel
import javax.inject.Inject

class DataVersionDataSourceImpl @Inject constructor(
    private val dataVersionDao: DataVersionDao
) : LocalDataVersionDataSource {
    override suspend fun getDataVersion(): LocalDataVersionDataModel? {
        Log.d("Ram Test", "getDataVersion")
        return dataVersionDao
            .getDataVersion()
            ?.run { LocalDataVersionMapper.toDataModel(this) }
    }

    override suspend fun insertDataVersion(localDataVersionEntity: LocalDataVersionDataModel) {
        Log.d("Ram Test", "insertDataVersion")
        dataVersionDao.insertDataVersion(
            LocalDataVersionMapper
                .toLocalEntity(localDataVersionEntity)
        )
    }

    override suspend fun deleteAllDataVersion() {
        Log.d("Ram Test", "deleteAllDataVersion")
        dataVersionDao.deleteAllDataVersion()
    }
}