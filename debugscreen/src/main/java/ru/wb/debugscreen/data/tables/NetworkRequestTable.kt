package ru.wb.debugscreen.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.wb.debugscreen.domain.entities.NetworkInfo
import ru.wb.debugscreen.domain.entities.NetworkRequest

@Entity(
    tableName = NetworkRequestTable.TABLE_NAME
)
data class NetworkRequestTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("is_successful")
    val isSuccessful: Boolean,
    @ColumnInfo("url")
    val url: String,
    @ColumnInfo("method")
    val method: String,
    @ColumnInfo("code")
    val code: Int,
    @ColumnInfo("bodyRequest")
    val bodyRequest: String? = null,
    @ColumnInfo("bodyResponse")
    val bodyResponse: String? = null
) {
    companion object {
        const val TABLE_NAME = "NetworkRequestTable"
    }
}

fun NetworkRequest.toTable(): NetworkRequestTable {
    return NetworkRequestTable(
        isSuccessful = isSuccessful,
        url = url,
        method = method,
        code = code,
        bodyRequest = request.body,
        bodyResponse = response.body
    )
}

fun NetworkRequestTable.toModel(): NetworkRequest {
    return NetworkRequest(
        isSuccessful = isSuccessful,
        url = url,
        method = method,
        code = code,
        request = NetworkInfo(
            header = emptyMap(),
            body = bodyRequest
        ),
        response = NetworkInfo(
            header = emptyMap(),
            body = bodyResponse
        )
    )
}