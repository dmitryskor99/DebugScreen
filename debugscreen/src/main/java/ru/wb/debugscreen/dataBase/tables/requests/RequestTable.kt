package ru.wb.debugscreen.dataBase.tables.requests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.wb.debugscreen.domain.entities.NetworkRequest
import java.util.Date

@Entity(
    tableName = RequestTable.TABLE_NAME
)
data class RequestTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("is_successful")
    val isSuccessful: Boolean,
    @ColumnInfo("time_send")
    val timeSend: Date,
    @ColumnInfo("time_received")
    val timeReceived: Long,
    @ColumnInfo("scheme")
    val scheme: String,
    @ColumnInfo("host")
    val host: String,
    @ColumnInfo("path")
    val path: String,
    @ColumnInfo("code_request")
    val codeRequest: Int,
    @ColumnInfo("method")
    val method: String,
    @ColumnInfo("request_body")
    val requestBody: String? = null,
    @ColumnInfo("response_body")
    val responseBody: String? = null
) {
    companion object {
        const val TABLE_NAME = "RequestsTable"
    }
}

fun NetworkRequest.toTableRequest(): RequestTable {
    return RequestTable(
        isSuccessful = isSuccessful,
        timeSend = timeSend,
        timeReceived = timeReceived,
        scheme = scheme,
        host = host,
        path = path,
        codeRequest = codeRequest,
        method = method,
        requestBody = request.body,
        responseBody = response.body
    )
}
