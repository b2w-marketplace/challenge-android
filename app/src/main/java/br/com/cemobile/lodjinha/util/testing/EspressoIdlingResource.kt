package br.com.cemobile.lodjinha.util.testing

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {

    private val countingIdlingResource =
            CountingIdlingResource(EspressoIdlingResource::class.java.simpleName)

    val idlingResource : IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }

}