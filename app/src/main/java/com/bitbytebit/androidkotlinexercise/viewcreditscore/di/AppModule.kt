package com.bitbytebit.androidkotlinexercise.viewcreditscore.di

import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.scope.PerApplication
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    @PerApplication
    fun provideApp() = app

}