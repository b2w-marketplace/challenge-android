package br.com.andremoreira.lodjinha.utils

import android.app.Dialog
import android.content.Context
import android.view.Window
import br.com.andremoreira.lodjinha.R
import br.com.andremoreira.lodjinha.di.ApplicationBase

class LoadingDialog(private val context: Context) {

    private var mLoadingDialog: Dialog? = null

    fun showLoading(lblLoading:String?=null){
        create()
        mLoadingDialog!!.show()
    }

    fun create(){
        mLoadingDialog = Dialog(ApplicationBase.activityContext, R.style.transparentBgTheme)
        mLoadingDialog?.let {
            it.requestWindowFeature(Window.FEATURE_NO_TITLE)
//        mLoadingDialog.setOwnerActivity(act)
            it.setContentView(R.layout.dialog_loading)
            it.setCancelable(true)
            //ncessario para rodar no context do ApplicationBase
//            it.getWindow().setType(WindowManager.LayoutParams.TYPE_PHONE);
        }
    }

    fun dismissLoading() {
        if (mLoadingDialog != null) {
            mLoadingDialog!!.dismiss()
            mLoadingDialog = null
        }
    }
}