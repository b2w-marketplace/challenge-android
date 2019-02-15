package com.bryanollivie.lojinha.utils

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Toast
import com.bryanollivie.lojinha.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
    val format = SimpleDateFormat("yyyy-M-dd", Locale.US)
    val date = initialFormat.parse(this)
    return format.format(date).toString()
}

fun Activity.isValid(editText: EditText, min: Int): Boolean {
    var formValido = false

    if (editText.editableText.isNullOrBlank()) {
        erroFocus(editText, "Campo Obrigatório!")
    } else if ((editText.length() < min)) {
        erroFocus(editText, "Minimo de $min Caracteres!")
    } else {
        formValido = true
    }
    return formValido
}

fun Activity.isValid(editText: EditText, min: Int, mensagem: String): Boolean {
    var formValido = false

    if (editText.editableText.isNullOrBlank()) {
        erroFocus(editText, "Campo Obrigatório!")
    } else if ((editText.length() < min)) {
        erroFocus(editText, mensagem)
    } else {
        formValido = true
    }
    return formValido
}


fun Activity.isValidEmail(editText: EditText): Boolean {
    var formValido = false

    if (editText.editableText.isNullOrBlank()) {
        erroFocus(editText, "Campo Obrigatório!")
    } else if (!editText.editableText.toString().contains("@")) {
        erroFocus(editText, "Email Invalido!")
    } else {
        formValido = true
    }
    return formValido
}

fun Activity.erroFocus(editText: EditText, msg: String) {
    editText.error = msg
    editText.requestFocus()
}


fun Activity.erroFocus(radioButton: RadioButton, msg: String) {
    radioButton.error = msg
    radioButton.requestFocus()
}

fun Activity.isPasswordValid(password: EditText, confirmPassword: EditText): Boolean {
    var valid = false
    if (isValid(password, 8) and isValid(confirmPassword, 8)) {

        if (password.editableText.toString().equals(confirmPassword.editableText.toString())) {
            valid = true
        } else {
            erroFocus(confirmPassword, "As senhas tem que ser iguais!")
            valid = false
        }
    }
    return valid
}

fun Activity.removeMask(s: String): String {
    return s.replace("[.]".toRegex(), "").replace("[-]".toRegex(), "")
            .replace("[/]".toRegex(), "").replace("[(]".toRegex(), "")
            .replace("[)]".toRegex(), "")
}


fun Activity.insertMask(mask: String, ediTxt: EditText?): TextWatcher {
    return object : TextWatcher {
        internal var isUpdating: Boolean = false
        internal var old = ""

        override fun onTextChanged(s: CharSequence, start: Int, before: Int,
                                   count: Int) {


            val str = removeMask(s.toString())
            var mascara = ""
            if (isUpdating) {
                old = str
                isUpdating = false
                return
            }
            var i = 0
            for (m in mask.toCharArray()) {
                if (m != '#' && str.length > old.length) {

                    mascara += m

                    continue
                }
                try {
                    mascara += str.get(i)
                } catch (e: Exception) {
                    break
                }

                i++
            }
            isUpdating = true
            if (ediTxt != null) {
                ediTxt.setText(mascara)
                ediTxt.setSelection(mascara.length)
            }
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int,
                                       after: Int) {
        }

        override fun afterTextChanged(s: Editable) {}
    }


}


//Share And Open
fun Activity.openFile(context: Context, uriFile: String, type: String) {
    val file = File(uriFile)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(Uri.fromFile(file), type)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    try {
        context.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(context, "Não foi possivel abrir o arquivo.", Toast.LENGTH_LONG).show()
    }

}

fun Activity.shareFile(context: Context, uri: Uri, type: String) {
    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
    shareIntent.type = type
    context.startActivity(Intent.createChooser(shareIntent, context.resources.getText(R.string.abc_shareactionprovider_share_with_application)))
}


fun Activity.share(context: Context, uri_share: String, type: String) {

    val shareIntent = Intent()
    shareIntent.action = Intent.ACTION_SEND
    shareIntent.putExtra(Intent.EXTRA_TEXT, uri_share)
    shareIntent.type = type
    context.startActivity(Intent.createChooser(shareIntent, context.resources.getText(R.string.abc_shareactionprovider_share_with)))
}




