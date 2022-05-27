package com.example.baseandroidkotlinmvi.di

import android.content.Context
import com.example.baseandroidkotlinmvi.data.repository_impl.SampleRepositoryImpl
import com.example.baseandroidkotlinmvi.data.source.remote.ISampleApi
import com.example.baseandroidkotlinmvi.domain.preferences.PrefsHelper
import com.example.baseandroidkotlinmvi.domain.repository.ISampleRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    private val BASE_URL = "https://api.sampleapis.com/csscolornames/"

    private val READ_TIMEOUT = 30
    private val WRITE_TIMEOUT = 30
    private val CONNECTION_TIMEOUT = 10
    private val CACHE_SIZE_BYTES = 10 * 1024 * 1024L

    @Provides
    @Singleton
    fun providesRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesOkHttpClient(
        @ApplicationContext context: Context,
        prefsHelper: PrefsHelper
    ): OkHttpClient {
        val mCache = Cache(context.cacheDir, CACHE_SIZE_BYTES)
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .cache(mCache) // make your app offline-friendly without a database!
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addNetworkInterceptor(interceptor)
            .addInterceptor { chain ->
                var request = chain.request()
                request =
                    if (true) request
                        .newBuilder()
                        .header("Cache-Control", "public, max-age=" + 5)
//                        .header("token", prefsHelper.readString("jjj", ""))
                        .build()
                    else request
                        .newBuilder()
                        .header(
                            "Cache-Control",
                            "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7
                        )
                        .build()
                chain.proceed(request)
            }
        return client.build()
    }


    @Provides
    @Singleton
    fun providesGson(): Gson {
        return Gson()
    }

    @Provides
    @Singleton
    fun providesGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }


    //di API
    @Provides
    @Singleton
    fun providesSampleApi(retrofit: Retrofit): ISampleApi {
        return retrofit.create(ISampleApi::class.java)
    }

    //di REPOSITORY
    @Provides
    @Singleton
    fun providesSampleRepository(api: ISampleApi): ISampleRepository {
        return SampleRepositoryImpl(api)
    }

}