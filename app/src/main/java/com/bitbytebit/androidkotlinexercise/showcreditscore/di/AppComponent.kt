package com.bitbytebit.androidkotlinexercise.showcreditscore.di

import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.scope.PerApplication
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, CreditScoreRepositoryModule::class])
interface AppComponent {

    fun inject(app: App)

    fun plus(module: ShowCreditScoreActivityModule) : ShowCreditScoreActivityComponent
}