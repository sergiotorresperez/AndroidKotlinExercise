package com.bitbytebit.androidkotlinexercise.viewcreditscore.di

import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.scope.PerApplication
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.di.ShowCreditScoreActivityComponent
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.di.ShowCreditScoreActivityModule
import dagger.Component

@PerApplication
@Component(modules = [AppModule::class, CreditScoreRepositoryModule::class])
interface AppComponent {

    fun inject(app: App)

    fun plus(module: ShowCreditScoreActivityModule) : ShowCreditScoreActivityComponent
}