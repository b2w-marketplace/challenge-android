package com.bryanollivie.lojinha.ui.sobre

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bryanollivie.lojinha.R
import kotlinx.android.synthetic.main.activity_sobre.*


class SobreActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sobre)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

}
