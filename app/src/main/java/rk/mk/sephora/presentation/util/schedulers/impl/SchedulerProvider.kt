package com.heetch.technicaltest.presentation.util.schedulers.impl

import com.heetch.technicaltest.presentation.util.schedulers.interfaces.BaseSchedulerProvider
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider : BaseSchedulerProvider {

    override fun computation() = Schedulers.computation()

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io() = Schedulers.io()
}