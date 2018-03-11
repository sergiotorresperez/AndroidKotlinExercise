package com.bitbytebit.androidkotlinexercise

import android.app.Application
import android.content.Context
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.AppComponent
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.AppModule
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.CreditScoreRepositoryModule
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.DaggerAppComponent

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .creditScoreRepositoryModule(CreditScoreRepositoryModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

}

fun Context.getApp() : App = this.applicationContext as App
