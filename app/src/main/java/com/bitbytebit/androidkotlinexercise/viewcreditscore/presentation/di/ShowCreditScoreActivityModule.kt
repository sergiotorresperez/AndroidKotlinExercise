package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.di

import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.scope.PerActivity
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.ShowCreditScorePresenter
import dagger.Module
import dagger.Provides

@Module
class ShowCreditScoreActivityModule(private val view : ShowCreditScorePresenter.View) {

    @Provides
    @PerActivity
    fun provideView() = view

}