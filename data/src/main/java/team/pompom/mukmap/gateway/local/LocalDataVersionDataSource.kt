package team.pompom.mukmap.gateway.local

import team.pompom.mukmap.model.local.LocalDataVersionDataModel

interface LocalDataVersionDataSource {
    suspend fun getDataVersion(): LocalDataVersionDataModel?
    suspend fun insertDataVersion(localDataVersionEntity: LocalDataVersionDataModel)
    suspend fun deleteAllDataVersion()
}