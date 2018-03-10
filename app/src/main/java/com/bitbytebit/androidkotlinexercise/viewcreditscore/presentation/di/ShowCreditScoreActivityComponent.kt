package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.di

import com.bitbytebit.androidkotlinexercise.viewcreditscore.di.scope.PerActivity
import com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation.ShowCreditScoreActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ShowCreditScoreActivityModule::class)])
interface ShowCreditScoreActivityComponent {

    fun inject(activity: ShowCreditScoreActivity)

}