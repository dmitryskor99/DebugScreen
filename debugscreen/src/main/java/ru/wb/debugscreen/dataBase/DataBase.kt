//package ru.wb.debugscreen.dataBase
//
//import androidx.room.Database
//import androidx.room.RoomDatabase
//import androidx.room.TypeConverters
//import ru.wb.debugscreen.dataBase.dao.RequestsDao
//import ru.wb.debugscreen.dataBase.dao.SubstitutionsDao
//import ru.wb.debugscreen.dataBase.tables.requests.HeaderRequestTable
//import ru.wb.debugscreen.dataBase.tables.requests.HeaderResponseTable
//import ru.wb.debugscreen.dataBase.tables.requests.QueryParamTable
//import ru.wb.debugscreen.dataBase.tables.requests.RequestTable
//import ru.wb.debugscreen.dataBase.typeConverter.DateConverter
//
//@Database(
//    entities = [
//        RequestTable::class,
//        QueryParamTable::class,
//        HeaderRequestTable::class,
//        HeaderResponseTable::class
//    ],
//    version = 1
//)
//@TypeConverters(DateConverter::class)
//internal abstract class DataBaseDebugScreen : RoomDatabase() {
//    abstract fun getRequestsDao(): RequestsDao
//    abstract fun getSubstitutionsDao(): SubstitutionsDao
//}