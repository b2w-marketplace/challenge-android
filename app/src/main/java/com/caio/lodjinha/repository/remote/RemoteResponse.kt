package com.caio.lodjinha.repository.remote

import android.util.Log
import io.reactivex.Observable
import retrofit2.Response

open class RemoteResponse<T>( val data: T? = null, val errorRepo: ErrorResponse? = null) {
    companion object {
        fun <T> fromData( data: T ) : RemoteResponse<T> {
            return RemoteResponse(data, null)
        }

        fun <T> fromError( errorRepo: ErrorResponse?) : RemoteResponse<T> {
            return RemoteResponse(null, errorRepo)
        }
    }

    fun isError() : Boolean{
        return errorRepo != null
    }

    fun isSuccess() : Boolean {
        return data != null
    }

    fun connectivityErrorObservable() : Observable<RemoteResponse<T?>>? {
        return Observable.just(fromError(ErrorResponse(ErrorConstant.CONNECTION_NOT)))
    }

}

fun <T> Observable<Response<T>>.validateHttpCode() : Observable<RemoteResponse<T?>> {
    return map {
        Log.d("validateHttpCode->", it.code().toString())
        if(it.code() == 200){
            RemoteResponse.fromData(it.body())
        }
        else
            RemoteResponse.fromError(ErrorResponse(ErrorConstant.SERVICE_NOT))
    }
}



