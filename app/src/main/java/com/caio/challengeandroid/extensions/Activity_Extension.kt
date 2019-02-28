package com.caio.lodjinha.extensions

import android.content.Context
import android.content.Intent
import android.os.Bundle

fun <T> Context.openActivity(it: Class<T>, extras: Bundle.() -> Unit = {}) {
    var intent = Intent(this, it)
    intent.putExtras(Bundle().apply(extras))
    startActivity(intent)
}