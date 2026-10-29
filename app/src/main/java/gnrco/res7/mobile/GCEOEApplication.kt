package gnrco.res7.mobile

import android.app.Application
import gnrco.res7.mobile.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class GCEOEApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@GCEOEApplication)
            modules(appModules)
        }
    }
}