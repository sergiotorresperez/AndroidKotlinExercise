package com.bitbytebit.androidkotlinexercise.showcreditscore.di

import com.bitbytebit.androidkotlinexercise.showcreditscore.di.scope.PerActivity
import com.bitbytebit.androidkotlinexercise.showcreditscore.presentation.ShowCreditScorePresenter
import dagger.Module
import dagger.Provides

@Module
class ShowCreditScoreActivityModule(private val view : ShowCreditScorePresenter.View) {

    @Provides
    @PerActivity
    fun provideView() = view

}