package br.com.dafle.alodjinha.util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import br.com.dafle.alodjinha.R
import kotlinx.android.synthetic.main.progress_dialog.*

class ProgressDialog: DialogFragment() {

    companion object {
        const val EXTRA_TITLE = "title"
    }

    private var title: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.progress_dialog, container, false)
        title = arguments?.getString(EXTRA_TITLE)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvMessageDialog?.text = title
    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.90).toInt()
        dialog.window?.setLayout(width, 500)
    }
}