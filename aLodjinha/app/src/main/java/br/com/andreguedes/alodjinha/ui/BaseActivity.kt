package br.com.andreguedes.alodjinha.ui

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initUI()

    protected lateinit var toolbar: Toolbar

    protected fun initToolbar(id: Int) {
        toolbar = findViewById<View>(id) as Toolbar
        setSupportActionBar(toolbar)
    }

}