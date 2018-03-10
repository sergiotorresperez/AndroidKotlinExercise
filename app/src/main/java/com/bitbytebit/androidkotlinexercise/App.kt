package com.bitbytebit.androidkotlinexercise

import android.app.Application
import android.content.Context
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.AppComponent
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.AppModule
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.CreditScoreRepositoryModule
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.DaggerAppComponent

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

fun Application.from(context : Context) : App = context.applicationContext as App
