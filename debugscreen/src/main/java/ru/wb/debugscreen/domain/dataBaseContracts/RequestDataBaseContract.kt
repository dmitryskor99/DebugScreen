package ru.wb.debugscreen.domain.dataBaseContracts

import kotlinx.coroutines.flow.Flow
import ru.wb.debugscreen.domain.entities.NetworkRequest

interface RequestDataBaseServiceContract {
    fun getRequests(): Flow<List<NetworkRequest>>

    suspend fun insertRequest(request: NetworkRequest)

    suspend fun deleteAll()
}