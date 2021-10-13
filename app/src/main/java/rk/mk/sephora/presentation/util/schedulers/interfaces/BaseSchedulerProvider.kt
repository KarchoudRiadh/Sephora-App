package com.heetch.technicaltest.presentation.util.schedulers.interfaces

import io.reactivex.Scheduler

/**
 * This interface represents the contract for providing schedulers used with observables.
 */
interface BaseSchedulerProvider {

    /**
     * This is one of the most common types of Schedulers that are used.
     * They are generally used for IO related stuff. Such as network requests, file system operations.
     * IO scheduler is backed by thread-pool.
     *
     * @return [Scheduler]
     */
    fun io(): Scheduler

    /**
     * Returns a default, shared Scheduler instance intended for computational work.
     * This can be used for event-loops, processing callbacks and other computational work.
     *
     * @return [Scheduler]
     */
    fun computation(): Scheduler

    /**
     * A Scheduler which executes actions on the Android main thread for UI handling.
     *
     * @return [Scheduler]
     */
    fun ui(): Scheduler
}