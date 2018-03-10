package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AndroidSchedulersProvider : SchedulersProvider {
    override fun getExecutionScheduler(): Scheduler = Schedulers.io()
    override fun getPostExecutionScheduler(): Scheduler = AndroidSchedulers.mainThread()
}