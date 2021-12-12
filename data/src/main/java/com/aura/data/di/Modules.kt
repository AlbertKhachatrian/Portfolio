package com.aura.data.di

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.aura.core.BuildConfig
import com.aura.data.dataservice.apiservice.DashboardApi
import com.aura.data.dataservice.appservice.DataSource
import com.aura.data.dataservice.appservice.DataSourceImpl
import com.aura.data.datastore.DashboardRepository
import com.aura.data.repository.DashboardRepositoryImpl
import com.aura.data.room.AppDatabase
import com.aura.data.util.TokenInterceptor
import okhttp3.Cache
import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val dataServiceModule = module {

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder()
                .cache(
                    Cache(
                        androidApplication().cacheDir,
                        10L * 1024 * 1024 //10mb
                    )
                )
                .callTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(TokenInterceptor())
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build())
            .build()
    }

    single<DashboardApi> { get<Retrofit>().create(DashboardApi::class.java) }

}

val sqlServiceModule = module {
    fun provideDatabase(application: Application): AppDatabase{
        return Room.databaseBuilder(application, AppDatabase::class.java, "appDb")
            .build()
    }
    single { provideDatabase(androidApplication()) }

    single { get<AppDatabase>().bonusDao() }
    single { get<AppDatabase>().gradeDao() }
    single { get<AppDatabase>().profitDao() }
    single { get<AppDatabase>().refillDao() }
}

val repoModule = module {

    single<DataSource> { DataSourceImpl(androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager) }

    single<DashboardRepository> { DashboardRepositoryImpl(get(), get(),get(), get(), get(), get()) }
}