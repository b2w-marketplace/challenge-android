package br.com.prodigosorc.lodjinha.ui.activities

import android.support.v7.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    fun setActionBarTitle(title: String) {
        supportActionBar!!.title = title
    }
}