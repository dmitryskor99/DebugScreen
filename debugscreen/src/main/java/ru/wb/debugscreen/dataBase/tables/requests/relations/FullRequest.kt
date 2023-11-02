package ru.wb.debugscreen.dataBase.tables.requests.relations

import androidx.room.Embedded
import androidx.room.Relation
import ru.wb.debugscreen.dataBase.tables.requests.HeaderRequestTable
import ru.wb.debugscreen.dataBase.tables.requests.HeaderResponseTable
import ru.wb.debugscreen.dataBase.tables.requests.QueryParamTable
import ru.wb.debugscreen.dataBase.tables.requests.RequestTable
import ru.wb.debugscreen.dataBase.tables.requests.toModel
import ru.wb.debugscreen.domain.entities.NetworkInfo
import ru.wb.debugscreen.domain.entities.NetworkRequest
import ru.wb.debugscreen.utils.asJsonFormattedView

data class FullRequest(
    @Embedded
    val request: RequestTable,
    @Relation(
        parentColumn = "id",
        entityColumn = "request_id"
    )
    val queriesRequest: List<QueryParamTable>,
    @Relation(
        parentColumn = "id",
        entityColumn = "request_id"
    )
    val headersRequest: List<HeaderRequestTable>,
    @Relation(
        parentColumn = "id",
        entityColumn = "request_id"
    )
    val headersResponse: List<HeaderResponseTable>
)

fun FullRequest.toModel(): NetworkRequest {
    return NetworkRequest(
        isSuccessful = request.isSuccessful,
        timeSend = request.timeSend,
        timeReceived = request.timeReceived,
        scheme = request.scheme,
        host = request.host,
        path = request.path,
        queryParams = queriesRequest.toModel(),
        method = request.method,
        codeRequest = request.codeRequest,
        request = NetworkInfo(
            header = headersRequest.toModel(),
            body = if (request.requestBody.isNullOrEmpty())
                null
            else request.requestBody.asJsonFormattedView()
        ),
        response = NetworkInfo(
            header = headersResponse.toModel(),
            body = if (request.responseBody.isNullOrEmpty())
                null
            else request.responseBody.asJsonFormattedView()
        )
    )
}