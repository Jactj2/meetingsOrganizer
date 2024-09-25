package org.yomasoftware.project

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import org.yomasoftware.project.data.DriverFactory
import com.yomasoftware.data.server.SQLDelightSource
import org.yomasoftware.project.data.repositories.LocalDataSource
import org.yomasoftware.project.di.appViewModel
import org.yomasoftware.project.di.core
import org.yomasoftware.project.di.repositories
import org.yomasoftware.project.di.weekViewModel

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@MainApplication)
            modules(
                module {
                    single<LocalDataSource> {
                        com.yomasoftware.data.server.SQLDelightSource(
                            DriverFactory(this@MainApplication).createDriver()
                        )
                    }
                },
                core,
                repositories,
                weekViewModel,
                appViewModel,
            )
        }
        }
}
