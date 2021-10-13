package com.heetch.technicaltest.presentation.util.schedulers.impl

import com.heetch.technicaltest.presentation.util.schedulers.interfaces.BaseSchedulerProvider
import io.reactivex.schedulers.Schedulers

/**
 * Schedules work on the current thread but does not execute immediately.
 * Work is put in a queue and executed after the current unit of work is completed.
 */
class TrampolineSchedulerProvider : BaseSchedulerProvider {
    override fun computation() = Schedulers.trampoline()
    override fun ui() = Schedulers.trampoline()
    override fun io() = Schedulers.trampoline()
}