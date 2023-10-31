package ru.wb.debugscreen.domain

import android.content.Context
import java.util.Locale

class DebugScreenConfig private constructor() {
//    private var _dataBase: DataBaseDebugScreen? = null
    private var _locale: Locale? = null
//    internal val dataBase: DataBaseDebugScreen get() {
//        require(_dataBase != null) {
//            "The context was not passed to the initializeApp function of the DebugScreenConfig class"
//        }
//        return _dataBase!!
//    }

    internal val locale: Locale? get() = _locale

    internal fun createDataBase(context: Context, locale: Locale?) {
        _locale = locale
//        _dataBase = Room
//            .databaseBuilder(context, DataBaseDebugScreen::class.java, "dataBaseDebugScreen")
//            .build()
    }

    companion object {

        internal val config: DebugScreenConfig = DebugScreenConfig()

        fun initializeApp(context: Context, locale: Locale) {
            config.createDataBase(context = context, locale = locale)
        }

        fun initializeApp(context: Context) {
            config.createDataBase(context = context, locale = null)
        }
    }
}