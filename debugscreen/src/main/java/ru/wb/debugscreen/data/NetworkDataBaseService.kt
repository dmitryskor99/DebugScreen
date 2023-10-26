package ru.wb.debugscreen.data

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.wb.debugscreen.data.tables.toModel
import ru.wb.debugscreen.data.tables.toTable
import ru.wb.debugscreen.domain.DebugScreenConfig
import ru.wb.debugscreen.domain.entities.NetworkRequest

object NetworkDataBaseService {

    fun getNetworkRequests(): Flow<List<NetworkRequest>>? {
        return DebugScreenConfig.dataBase?.getNetworkDao()?.getNetworkRequests()?.map { requests ->
            requests.map { it.toModel() }
        }
    }

    suspend fun upsertNetworkRequest(networkRequest: NetworkRequest) {
        DebugScreenConfig.dataBase?.getNetworkDao()?.upsertNetworkRequest(networkRequest.toTable())
    }

    suspend fun deleteAll() {
        DebugScreenConfig.dataBase?.getNetworkDao()?.deleteAll()
    }
}