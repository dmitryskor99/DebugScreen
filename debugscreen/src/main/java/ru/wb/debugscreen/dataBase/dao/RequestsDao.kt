//package ru.wb.debugscreen.dataBase.dao
//
//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//import kotlinx.coroutines.flow.Flow
//import ru.wb.debugscreen.dataBase.tables.requests.HeaderRequestTable
//import ru.wb.debugscreen.dataBase.tables.requests.HeaderResponseTable
//import ru.wb.debugscreen.dataBase.tables.requests.QueryParamTable
//import ru.wb.debugscreen.dataBase.tables.requests.RequestTable
//import ru.wb.debugscreen.dataBase.tables.requests.relations.FullRequest
//import ru.wb.debugscreen.dataBase.tables.requests.toTableQueryParam
//import ru.wb.debugscreen.dataBase.tables.requests.toTableRequest
//import ru.wb.debugscreen.domain.entities.NetworkRequest
//
//@Dao
//internal interface RequestsDao {
//
//    @Transaction
//    @Query("SELECT * FROM ${RequestTable.TABLE_NAME}")
//    fun getRequests(): Flow<List<FullRequest>>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRequest(request: RequestTable): Long
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRequestQueries(queries: List<QueryParamTable>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertRequestHeaders(header: HeaderRequestTable)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertResponseHeaders(header: HeaderResponseTable)
//
//    @Transaction
//    suspend fun insert(networkRequest: NetworkRequest) {
//        val requestId = insertRequest(networkRequest.toTableRequest())
//        insertRequestQueries(networkRequest.queryParams.toTableQueryParam(requestId))
//        networkRequest.request.header.forEach {
//            insertRequestHeaders(
//                header = HeaderRequestTable(
//                    requestId = requestId,
//                    name = it.key,
//                    value = it.value
//                )
//            )
//        }
//        networkRequest.response.header.forEach {
//            insertResponseHeaders(
//                header = HeaderResponseTable(
//                    requestId = requestId,
//                    name = it.key,
//                    value = it.value
//                )
//            )
//        }
//    }
//
//    @Query("DELETE FROM ${RequestTable.TABLE_NAME}")
//    suspend fun deleteAll()
//}