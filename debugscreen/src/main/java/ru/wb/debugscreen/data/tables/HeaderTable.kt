package ru.wb.debugscreen.data.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = HeaderTable.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = HeadersTable::class,
            parentColumns = ["id"],
            childColumns = ["id_header"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class HeaderTable(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("id_header")
    val idHeader: Int,
    @ColumnInfo("value")
    val value: String
) {
    companion object {
        const val TABLE_NAME = "HeaderTable"
    }
}