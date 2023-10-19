package com.fakhry.livestream.factory

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.fakhry.livestream.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HttpFactory {
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://open.byteplusapi.com/")
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    fun crateChuckerInterceptor(context: Context): ChuckerInterceptor {
        val appContext = context.applicationContext
        val chuckerCollector = ChuckerCollector(
            context = appContext,
            showNotification = BuildConfig.DEBUG,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )

        return ChuckerInterceptor.Builder(appContext).collector(chuckerCollector)
            .maxContentLength(250000L).redactHeaders(emptySet()).alwaysReadResponseBody(true)
            .build()
    }

    fun createOkhttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        /*chuckerInterceptor: ChuckerInterceptor,*/
    ): OkHttpClient {

        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)/*.addInterceptor(chuckerInterceptor)*/
            .connectTimeout(10, TimeUnit.SECONDS).writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS).build()
    }

    fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }
}