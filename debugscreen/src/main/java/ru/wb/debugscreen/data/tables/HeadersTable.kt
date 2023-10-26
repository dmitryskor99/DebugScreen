package ru.wb.debugscreen.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = HeadersTable.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = NetworkRequestTable::class,
            parentColumns = ["id"],
            childColumns = ["id_request"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class HeadersTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("id_request")
    val idRequest: Int
) {
    companion object {
        const val TABLE_NAME = "HeadersTable"
    }
}