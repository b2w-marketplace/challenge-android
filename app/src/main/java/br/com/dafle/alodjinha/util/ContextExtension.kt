package br.com.dafle.alodjinha.util

import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Point
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import br.com.dafle.alodjinha.base.BaseViewModel

fun Context.getDisplayWidth(): Int {
    val display = (this as AppCompatActivity).windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.x
}

fun Context.getDisplayHeight(): Int {
    val display = (this as AppCompatActivity).windowManager.defaultDisplay
    val size = Point()
    display.getSize(size)
    return size.y
}

fun Context.isNetworkConnected(): Boolean {
    val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
    return cm?.activeNetworkInfo?.isConnected ?: false
}

fun Context.toggleAndroidComponent(componentClass: Class<*>, enable: Boolean) {
    val componentName = ComponentName(this, componentClass)

    val newState = if (enable)
        PackageManager.COMPONENT_ENABLED_STATE_ENABLED
    else
        PackageManager.COMPONENT_ENABLED_STATE_DISABLED

    packageManager.setComponentEnabledSetting(componentName, newState, PackageManager.DONT_KILL_APP)
}
