package com.b2w.lodjinha.ui

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.widget.Toast
import com.b2w.lodjinha.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    fun showDefaultError(){
        Toast.makeText(this, R.string.default_error_message, Toast.LENGTH_LONG).show()
    }
}
