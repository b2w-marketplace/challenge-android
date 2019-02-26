package marcus.com.br.b2wtest.util

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Observer

class ResourceLiveData<T> : MutableLiveData<Resource<T>>() {

    fun observeResource(
        owner: LifecycleOwner,
        onSuccess: (T) -> Unit,
        onError: (Resource<T>) -> Unit
    ) {

        observe(owner, Observer<Resource<T>> {
            if (it!!.status == Status.SUCCESS) {
                onSuccess.invoke(it.data!!)
            } else {
                onError.invoke(it)
            }
        })
    }
}