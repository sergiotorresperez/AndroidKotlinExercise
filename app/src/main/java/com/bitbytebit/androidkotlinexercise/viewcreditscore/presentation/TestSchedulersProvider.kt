package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestSchedulersProvider : SchedulersProvider {
    override fun getExecutionScheduler(): Scheduler = Schedulers.trampoline()
    override fun getPostExecutionScheduler(): Scheduler = Schedulers.trampoline()
}
