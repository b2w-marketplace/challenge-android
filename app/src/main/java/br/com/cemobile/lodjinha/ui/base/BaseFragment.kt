package br.com.cemobile.lodjinha.ui.base

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import kotlin.reflect.KClass

abstract class BaseFragment : Fragment() {

    fun <T: ViewModel> providesOf(modelClass: KClass<T>, factory: ViewModelProvider.Factory): T =
            ViewModelProviders.of(this, factory).get(modelClass.java)

    abstract fun getTagName(): String

}