package com.bitbytebit.androidkotlinexercise.viewcreditscore.di

import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.scope.PerApplication
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.AndroidSchedulersProvider
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.SchedulersProvider
import dagger.Module
import dagger.Provides

@Module
class AppModule(val app: App) {

    @Provides
    @PerApplication
    fun provideApp() = app

    @Provides
    @PerApplication
    fun provideSchedulersProvider() : SchedulersProvider = AndroidSchedulersProvider()
}