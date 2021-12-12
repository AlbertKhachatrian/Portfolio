package com.aura.portfolio

import android.app.Application
import com.aura.data.di.dataServiceModule
import com.aura.data.di.repoModule
import com.aura.data.di.sqlServiceModule
import com.aura.domain.di.domainModule
import com.aura.portfolio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class PortfolioApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(
                dataServiceModule,
                sqlServiceModule,
                repoModule,
                domainModule,
                appModule
            )
        }
    }
}