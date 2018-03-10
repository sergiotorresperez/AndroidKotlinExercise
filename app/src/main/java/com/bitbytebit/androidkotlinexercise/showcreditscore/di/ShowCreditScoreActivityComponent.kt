package com.bitbytebit.androidkotlinexercise.showcreditscore.di

import com.bitbytebit.androidkotlinexercise.showcreditscore.di.scope.PerActivity
import com.bitbytebit.androidkotlinexercise.showcreditscore.presentation.ShowCreditScoreActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ShowCreditScoreActivityModule::class)])
interface ShowCreditScoreActivityComponent {

    fun inject(activity: ShowCreditScoreActivity)

}