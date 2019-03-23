package br.com.rbueno.lodjinha.util

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

fun <T> LiveData<T>.observe(owner: LifecycleOwner, func: (T) -> Unit) =
    observe(owner, Observer<T> { it?.let { func(it) } })