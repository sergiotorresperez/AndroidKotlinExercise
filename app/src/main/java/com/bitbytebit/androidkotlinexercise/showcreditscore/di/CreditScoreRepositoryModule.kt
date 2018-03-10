package com.bitbytebit.androidkotlinexercise.showcreditscore.di

import android.content.Context
import android.content.SharedPreferences
import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.BuildConfig
import com.bitbytebit.androidkotlinexercise.showcreditscore.data.CreditScoreService
import com.bitbytebit.androidkotlinexercise.showcreditscore.data.MemoryCreditScoreDataSource
import com.bitbytebit.androidkotlinexercise.showcreditscore.data.NetworkCreditScoreDataSource
import com.bitbytebit.androidkotlinexercise.showcreditscore.data.SharedPrefsScoreDataSource
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.scope.PerApplication
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScoreDataSource
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.CreditScoreRepository
import com.bitbytebit.androidkotlinexercise.showcreditscore.domain.TwoLevelCacheCreditScoreRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Qualifier

@Module
class CreditScoreRepositoryModule {

    companion object {
        private const val SHARED_PREFS_NAME = "CreditScoreLocalStorage"
    }

    @Provides
    @PerApplication
    @ForCreditScore
    fun provideSharedPreferences(app: App) : SharedPreferences {
        return app.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)
    }

    @Provides
    @PerApplication
    fun provideCreditScoreService() : CreditScoreService {

        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }

        val okHttpClient = okHttpBuilder
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl(BuildConfig.CREDIT_SCORE_API_BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create())
                .build()

        return retrofit.create(CreditScoreService::class.java)
    }

    @Provides
    @PerApplication
    @ForCacheLevel(level = CacheLevel.NETWORK)
    fun provideNetworkCreditScoreDataSource(service: CreditScoreService) : CreditScoreDataSource {
        return NetworkCreditScoreDataSource(service)
    }

    @Provides
    @PerApplication
    @ForCacheLevel(level = CacheLevel.LOCAL_STORAGE)
    fun provideLocalStorageCreditScoreDataSource(@ForCreditScore sharedPreferences: SharedPreferences) : CreditScoreDataSource {
        return SharedPrefsScoreDataSource(sharedPreferences)
    }

    @Provides
    @PerApplication
    @ForCacheLevel(level = CacheLevel.MEMORY)
    fun provideMemoryCreditScoreDataSource() : CreditScoreDataSource {
        return MemoryCreditScoreDataSource()
    }

    @Provides
    @PerApplication
    fun provideCreditScoreRepository(
            @ForCacheLevel(level = CacheLevel.NETWORK) networkDataSource: CreditScoreDataSource,
            @ForCacheLevel(level = CacheLevel.LOCAL_STORAGE) localStorageDataSource: CreditScoreDataSource,
            @ForCacheLevel(level = CacheLevel.MEMORY) memoryDataSource: CreditScoreDataSource) : CreditScoreRepository {

        return TwoLevelCacheCreditScoreRepository(networkDataSource, localStorageDataSource, memoryDataSource)
    }

}

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ForCreditScore()

@Qualifier
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ForCacheLevel(val level: CacheLevel)

enum class CacheLevel {
    NETWORK,
    LOCAL_STORAGE,
    MEMORY
}