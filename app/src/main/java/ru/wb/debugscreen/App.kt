package ru.wb.debugscreen

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.wb.debugscreen.data.NetworkDataBase
import ru.wb.debugscreen.data.tables.HeaderTable
import ru.wb.debugscreen.data.tables.HeadersTable
import ru.wb.debugscreen.data.tables.NetworkRequestTable
import ru.wb.debugscreen.domain.DebugScreenConfig

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        DebugScreenConfig.dataBase = Room.databaseBuilder(this, TestDataBase::class.java, "database").build()
    }
}

@Database(
    entities = [
        NetworkRequestTable::class,
        HeadersTable::class,
        HeaderTable::class
    ],
    version = 1
)
abstract class TestDataBase : RoomDatabase(), NetworkDataBase {
}