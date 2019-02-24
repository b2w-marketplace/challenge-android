package br.com.prodigosorc.lodjinha.ui.dialogs

import android.support.v4.app.FragmentActivity
import br.com.prodigosorc.lodjinha.util.Constants.Companion.BOTAO_OK

class CreateDialog {

    fun display(
        activity: FragmentActivity?,
        mensagem: String,
        finaliza: Boolean
    ) {
        val dialog = android.app.AlertDialog.Builder(activity)
            .setMessage(mensagem)
            .setCancelable(false)
            .setPositiveButton(BOTAO_OK) { _, _ ->
                if (finaliza) {
                    activity?.finish()
                }
            }
        dialog.show()
    }
}