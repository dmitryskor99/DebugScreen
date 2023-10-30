package ru.wb.debugscreen.dataBase.tables.requests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = QueryParamTable.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = RequestTable::class,
            parentColumns = [ "id" ],
            childColumns = [ "request_id" ],
            onUpdate = ForeignKey.CASCADE,
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class QueryParamTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 0,
    @ColumnInfo(name = "request_id")
    val requestId: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "value")
    val value: String? = null
) {
    companion object {
        const val TABLE_NAME = "QueryParamTable"
    }
}

fun Map<String, String?>.toTableQueryParam(requestId: Long): List<QueryParamTable> {
    return map {
        QueryParamTable(
            requestId = requestId,
            name = it.key,
            value = it.value
        )
    }
}

fun List<QueryParamTable>.toModel(): Map<String, String?> {
    return associate { Pair(it.name, it.value) }
}
