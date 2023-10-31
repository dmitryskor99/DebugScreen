//package ru.wb.debugscreen.dataBase.tables.requests
//
//import androidx.room.ColumnInfo
//import androidx.room.Entity
//import androidx.room.PrimaryKey
//import ru.wb.debugscreen.domain.entities.NetworkRequest
//import java.util.Date
//
//@Entity(
//    tableName = RequestTable.TABLE_NAME
//)
//data class RequestTable(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    val id: Long = 0,
//    @ColumnInfo(name = "is_successful")
//    val isSuccessful: Boolean,
//    @ColumnInfo(name = "time_send")
//    val timeSend: Date,
//    @ColumnInfo(name = "time_received")
//    val timeReceived: Long,
//    @ColumnInfo(name = "scheme")
//    val scheme: String,
//    @ColumnInfo(name = "host")
//    val host: String,
//    @ColumnInfo(name = "path")
//    val path: String,
//    @ColumnInfo(name = "code_request")
//    val codeRequest: Int,
//    @ColumnInfo(name = "method")
//    val method: String,
//    @ColumnInfo(name = "request_body")
//    val requestBody: String? = null,
//    @ColumnInfo(name = "response_body")
//    val responseBody: String? = null
//) {
//    companion object {
//        const val TABLE_NAME = "RequestsTable"
//    }
//}
//
//fun NetworkRequest.toTableRequest(): RequestTable {
//    return RequestTable(
//        isSuccessful = isSuccessful,
//        timeSend = timeSend,
//        timeReceived = timeReceived,
//        scheme = scheme,
//        host = host,
//        path = path,
//        codeRequest = codeRequest,
//        method = method,
//        requestBody = request.body,
//        responseBody = response.body
//    )
//}
