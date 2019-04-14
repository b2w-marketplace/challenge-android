package br.com.dafle.alodjinha.base

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.dafle.alodjinha.LodjinhaApplication
import br.com.dafle.alodjinha.util.isNetworkConnected
import io.reactivex.disposables.CompositeDisposable
import org.koin.dsl.module.applicationContext
import retrofit2.HttpException

open class BaseViewModel(private val app: Application, protected val context: Context = app): ViewModel() {

    protected val bag = CompositeDisposable()
    val progress = MutableLiveData<Boolean>()
    val errorState = MutableLiveData<String>()


    protected fun handleError(throwable: Throwable) {
        progress.value = false
        if (!app.isNetworkConnected()) {
            errorState.value = "Você não está conectado na internet"
            return
        }
        val message = when (throwable) {
            is HttpException -> when (throwable.code()) {
                    401 -> "Tente novamente mais tarde"

                    403 -> "Ocorreu um erro ${throwable.code()}"

                    404 -> "Serviço não encontrado ${throwable.code()}"

                    else -> "Ops, ocorreu em erro, tente novamente 0002"
                }
            else -> "Ocorreu um erro por favor tente novamente 0001"
        }
        Log.e("error", message)
        errorState.value = message
    }
}