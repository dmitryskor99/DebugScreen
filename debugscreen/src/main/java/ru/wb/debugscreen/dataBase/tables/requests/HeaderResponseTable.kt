package ru.wb.debugscreen.dataBase.tables.requests

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = HeaderResponseTable.TABLE_NAME,
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
data class HeaderResponseTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Long = 0,
    @ColumnInfo("request_id")
    val requestId: Long,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("value")
    val value: String
) {
    companion object {
        const val TABLE_NAME = "HeaderResponseTable"
    }
}

fun List<HeaderResponseTable>.toModel(): Map<String, String> {
    return associate { Pair(it.name, it.value) }
}