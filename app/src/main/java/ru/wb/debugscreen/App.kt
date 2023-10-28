package ru.wb.debugscreen

import android.app.Application
import android.os.Build
import ru.wb.debugscreen.domain.DebugScreenConfig

class App : Application() {
    @Suppress("DEPRECATION")
    override fun onCreate() {
        super.onCreate()
        DebugScreenConfig.initializeApp(
            context = this.applicationContext,
            locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                resources.configuration.locales.get(0)
            } else {
                resources.configuration.locale
            }
        )
    }
}