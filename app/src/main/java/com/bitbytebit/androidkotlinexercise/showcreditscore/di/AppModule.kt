package com.bitbytebit.androidkotlinexercise.showcreditscore.di

import com.bitbytebit.androidkotlinexercise.App
import com.bitbytebit.androidkotlinexercise.showcreditscore.di.scope.PerApplication
import com.bitbytebit.androidkotlinexercise.common.presentation.AndroidSchedulersProvider
import com.bitbytebit.androidkotlinexercise.common.presentation.SchedulersProvider
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