package com.bitbytebit.androidkotlinexercise.common.presentation

import io.reactivex.Scheduler

interface SchedulersProvider {
    fun getExecutionScheduler() : Scheduler
    fun getPostExecutionScheduler() : Scheduler
}