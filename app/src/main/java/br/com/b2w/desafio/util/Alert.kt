package br.com.b2w.desafio.util

import android.content.Context
import android.content.DialogInterface
import android.os.Build
import android.support.v7.app.AlertDialog
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView

object Alert {

    /**
     * Mostra um dialog simples com um botao OK
     *
     * @param message mensagem /conteudo que aparecera no dialog
     * @param context contexto da aplicacao
     */
    fun showDialogSimples(message: String, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.ALERT_AVISO)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
        } else {
            @Suppress("DEPRECATION")
            builder.setMessage(Html.fromHtml(message))
        }
        builder.setPositiveButton(Constantes.ALERT_OK, null)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Mostra um dialog simples com um botao OK
     *
     * @param icon mensagem /conteudo que aparecera no dialog
     * @param message mensagem /conteudo que aparecera no dialog
     * @param context contexto da aplicacao
     */
    fun showDialogSimples(icon: Int, message: String, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setIcon(icon)
        builder.setTitle(Constantes.ALERT_AVISO)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
        } else {
            @Suppress("DEPRECATION")
            builder.setMessage(Html.fromHtml(message))
        }
        builder.setPositiveButton(Constantes.ALERT_OK, null)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Mostra um dialog com um botao Sim e um botao Nao, com acao no botao Sim e
     * outra acao no botao Nao
     *
     * @param message   mensagem/conteudo que aparecera no dialog
     * @param context   contexto da aplicacao
     * @param botaoSim acao do botao Sim
     * @param botaoNao  acao do botao Nao
     */
    fun showDialogSimNao(message: String, context: Context,
                         botaoSim: DialogInterface.OnClickListener, botaoNao: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.ALERT_AVISO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
        } else {
            @Suppress("DEPRECATION")
            builder.setMessage(Html.fromHtml(message))
        }
        builder.setPositiveButton(Constantes.ALERT_SIM, botaoSim)
        builder.setNegativeButton(Constantes.ALERT_NAO, botaoNao)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Mostra um dialog com um botao Sim e um botao Nao, com acao no botao Sim e
     * outra acao no botao Nao
     *
     * @param message   mensagem/conteudo que aparecera no dialog
     * @param context   contexto da aplicacao
     * @param botaoOk acao do botao Ok
     * @param botaoCancelar  acao do botao Cancelar
     */
    fun showDialogOkCancelar(message: String, context: Context,
                             botaoOk: DialogInterface.OnClickListener, botaoCancelar: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.ALERT_AVISO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
        } else {
            @Suppress("DEPRECATION")
            builder.setMessage(Html.fromHtml(message))
        }
        builder.setPositiveButton(Constantes.ALERT_OK, botaoOk)
        builder.setNegativeButton(Constantes.ALERT_CANCELAR, botaoCancelar)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
    }

    /**
     * Mostra um dialog com um botao Sim e um botao Nao, com acao no botao Sim e
     * outra acao no botao Nao
     *
     * @param message   mensagem/conteudo que aparecera no dialog
     * @param context   contexto da aplicacao
     * @param botaoOk acao do botao Ok
     */
    fun showDialogOk(message: String, context: Context,
                     botaoOk: DialogInterface.OnClickListener) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(Constantes.ALERT_AVISO)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            builder.setMessage(Html.fromHtml(message, Html.FROM_HTML_MODE_LEGACY))
        } else {
            @Suppress("DEPRECATION")
            builder.setMessage(Html.fromHtml(message))
        }
        builder.setPositiveButton(Constantes.ALERT_OK, botaoOk)
        builder.setCancelable(false)
        val alertDialog = builder.create()
        alertDialog.show()

        (alertDialog.findViewById<View>(android.R.id.message) as TextView).movementMethod = LinkMovementMethod.getInstance()
    }
}
