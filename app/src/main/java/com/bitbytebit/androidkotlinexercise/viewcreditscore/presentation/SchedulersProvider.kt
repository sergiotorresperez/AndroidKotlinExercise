package com.bitbytebit.androidkotlinexercise.viewcreditscore.presentation

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun getExecutionScheduler() : Scheduler
    fun getPostExecutionScheduler() : Scheduler
}