package com.desafioamedigital.ui.base

import android.annotation.SuppressLint
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Gravity
import android.view.View
import com.desafioamedigital.R
import com.desafioamedigital.ui.activity.about.AboutActivity
import com.desafioamedigital.ui.activity.home.HomeActivity
import com.desafioamedigital.util.ErrorTypes
import com.desafioamedigital.util.isNetworkAvailable
import kotlinx.android.synthetic.main.progress_bar.*
import kotlinx.android.synthetic.main.toolbar_default.*
import retrofit2.HttpException

@SuppressLint("Registered")
open class BaseActivity : BaseContract.BaseView, AppCompatActivity() {

    override fun getContext() = this

    override fun showProgress() {
        progress_bar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progress_bar?.visibility = View.GONE
    }

    override fun configureToolbar(title: String) {
        setSupportActionBar(toolbar_default)
        setDefaultToolbarConfigurations()
        supportActionBar?.title = title
    }

    override fun configureToolbar(title: String, toolbar: Toolbar) {
        setSupportActionBar(toolbar)
        setDefaultToolbarConfigurations()
        supportActionBar?.title = title
    }

    override fun configureToolbar(toolbar: Toolbar) {
        setSupportActionBar(toolbar_default)
        setDefaultToolbarConfigurations()
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    override fun configureDrawer(drawer: DrawerLayout, toolbar: Toolbar, nv_menu: NavigationView) {
        setSupportActionBar(toolbar)
        val drawerToggle = ActionBarDrawerToggle(
            this, drawer, toolbar, 0, 0
        )
        drawer.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        nv_menu.itemIconTintList = null
        nv_menu.setNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.menu_item_home -> {
                    startActivity(Intent(getContext(), HomeActivity::class.java))
                }

                R.id.menu_item_about_app -> {
                    startActivity(Intent(getContext(), AboutActivity::class.java))
                }
            }

            drawer.closeDrawer(Gravity.START)
            finish()

            true
        }
    }

    override fun showAlertDialog(title: String, message: String, buttonText: String) {
        val builder = AlertDialog.Builder(this)

        builder
            .setMessage(message)
            .setPositiveButton(buttonText){ dialog, _ -> dialog?.dismiss()}

        val alert = builder.create()
        alert.setTitle(title)
        alert.show()
    }

    override fun showResponseError(error: Throwable) {

        val errorTitle = resources.getString(R.string.error_title)
        val alertButtonText = resources.getString(R.string.error_alert_button_text)

        if(!isNetworkAvailable()){
            showAlertDialog(errorTitle, resources.getString(R.string.error_no_connection_message), alertButtonText)
            return
        }

        if(error is HttpException){
            when(error.code()){

                ErrorTypes.UNAUTHORIZED -> {
                    showAlertDialog(errorTitle, resources.getString(R.string.error_unauthorized_message), alertButtonText)
                }

                ErrorTypes.FORBIDDEN -> {
                    showAlertDialog(errorTitle, resources.getString(R.string.error_forbidden_message), alertButtonText)
                }

                ErrorTypes.NOT_FOUND -> {
                    showAlertDialog(errorTitle, resources.getString(R.string.error_not_found_message), alertButtonText)
                }

                else -> {
                    showAlertDialog(errorTitle, resources.getString(R.string.error_unknown_message), alertButtonText)
                }
            }
        }else{
            showAlertDialog(errorTitle, resources.getString(R.string.error_unknown_message), alertButtonText)
        }

    }

    private fun setDefaultToolbarConfigurations(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

}