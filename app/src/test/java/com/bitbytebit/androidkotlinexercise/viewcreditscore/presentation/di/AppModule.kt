package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(val app: App) {
    @Provides
    @Singleton
    fun provideApp() = app
}