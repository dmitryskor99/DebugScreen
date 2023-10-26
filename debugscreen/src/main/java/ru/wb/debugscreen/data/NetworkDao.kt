package ru.wb.debugscreen.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow
import ru.wb.debugscreen.data.tables.NetworkRequestTable

@Dao
interface NetworkDao {

    @Query("SELECT * FROM ${NetworkRequestTable.TABLE_NAME}")
    fun getNetworkRequests(): Flow<List<NetworkRequestTable>>

    @Upsert
    suspend fun upsertNetworkRequest(networkRequest: NetworkRequestTable)

    @Query("DELETE FROM ${NetworkRequestTable.TABLE_NAME}")
    suspend fun deleteAll()
}