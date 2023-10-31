//package ru.wb.debugscreen
//
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.map
//import ru.wb.debugscreen.dataBase.tables.requests.relations.toModel
//import ru.wb.debugscreen.domain.DebugScreenConfig
//import ru.wb.debugscreen.domain.dataBaseContracts.RequestDataBaseServiceContract
//import ru.wb.debugscreen.domain.entities.NetworkRequest
//
//internal object RequestDataBaseService : RequestDataBaseServiceContract {
//    override fun getRequests(): Flow<List<NetworkRequest>> {
//        return DebugScreenConfig.config.dataBase.getRequestsDao().getRequests().map {
//            it.map { request -> request.toModel() }
//        }
//    }
//
//    override suspend fun insertRequest(request: NetworkRequest) {
//        DebugScreenConfig.config.dataBase.getRequestsDao().insert(request)
//    }
//
//    override suspend fun deleteAll() {
//        DebugScreenConfig.config.dataBase.getRequestsDao().deleteAll()
//    }
//}