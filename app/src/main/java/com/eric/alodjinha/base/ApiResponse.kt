package com.eric.alodjinha.base

class ApiResponse<T> {

    var code: Int? = null
        internal set
    var message: String? = null
        internal set
    var data: T? = null


}
