package com.b2w.lodjinha.data

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer

class ResourceLiveData<T> : SingleLiveData<Resource<T>>() {
    
    fun observeResource(
        owner: LifecycleOwner,
        onSuccess: (T) -> Unit,
        onError: (ResourceException?) -> Unit) {

        observe(owner, Observer { it ->
            if (it!!.status == Status.SUCCESS) {
                it.data?.let { onSuccess(it) }
            } else {
                onError.invoke(it.exception)
            }
        })
    }
}