package br.com.andrecouto.alodjinha.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.AssetManager
import android.graphics.Typeface
import android.support.annotation.StringDef
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.util.HashMap
import br.com.andrecouto.alodjinha.R

/**
 * Utilitário para cachear fontes customizadas.
 */
object TypefaceUtil {

    const val PACIFICO_REGULAR = "fonts/Pacifico-Regular.ttf"
    const val ROBOTO_REGULAR = "fonts/Roboto-Regular.ttf"
    const val ROBOTO_BOLD = "fonts/Roboto-Bold.ttf"
    const val ROBOTO_LIGHT = "fonts/Roboto-Light.ttf"
    const val ROBOTO_MEDIUM = "fonts/Roboto-Medium.ttf"

    val TAG = TypefaceUtil.javaClass.toString()

    private val FONTS_CACHE = HashMap<String, Typeface?>()

    /**
     * Validação customizada para os valores de fontes.
     */
    @StringDef(PACIFICO_REGULAR, ROBOTO_REGULAR, ROBOTO_BOLD, ROBOTO_LIGHT, ROBOTO_MEDIUM)
    @Retention(AnnotationRetention.RUNTIME)
    annotation class Fonts

    /**
     * Carrega uma fonte customizada usando o [AssetManager] do context passado e o caminho
     * relativo.
     *
     * @param ctx      Contexto para buscar o AssetManager
     * @param filePath Caminho do arquivo de fonte dentor de assets
     * @return Objeto Typeface
     */
    fun load(ctx: Context, @Fonts filePath: String): Typeface? {
        return load(ctx.assets, filePath)
    }

    /**
     * Carrega uma fonte customizada usando o [AssetManager] e o caminho relativo.
     *
     * @param assetManager AssetManager
     * @param filePath     Caminho do arquivo de fonte dentor de assets
     * @return Objeto Typeface
     */
    @SuppressLint("ThrowableNotAtBeginning")
    fun load(assetManager: AssetManager, @Fonts filePath: String): Typeface? {
        synchronized(FONTS_CACHE) {
            try {
                if (!FONTS_CACHE.containsKey(filePath)) {
                    val typeface = Typeface.createFromAsset(assetManager, filePath)
                    FONTS_CACHE[filePath] = typeface
                    return typeface
                }
            } catch (e: Exception) {
                Log.e(TAG, "Não foi possível ler a fonte " + e)
                FONTS_CACHE[filePath] = null
                return null
            }

            return FONTS_CACHE[filePath]
        }
    }

    fun setTypeface(view: TextView?, @Fonts font: String) {

        if (view == null || TextUtils.isEmpty(font)) {
            return
        }

        val typeface = load(view.context.assets, font)

        if (typeface == null) {
            Log.e(TAG,"Fonte não encontrada. Caminho: " +font)
            return
        }

        view.typeface = typeface
    }

    fun setTypeface(attrs: AttributeSet?, textView: TextView) {

        if (attrs == null) {
            Log.e(TAG,"Attrs não deveria estar nulo")
            return
        }

        val context = textView.context

        val values = context.obtainStyledAttributes(attrs, R.styleable.TypefacedTextView)
        val typefaceName = values.getString(R.styleable.TypefacedTextView_typeface)
        var typeface : Typeface? = null
        typefaceName?.let {
            typeface = load(textView.context.assets, typefaceName)
        }

        if (typeface != null) {
            textView.typeface = typeface
        }

        values.recycle()
    }

    fun setTypeface(attrs: AttributeSet?, button: Button) {

        if (attrs == null) {
            return
        }

        val context = button.context

        val values = context.obtainStyledAttributes(attrs, R.styleable.TypefacedButtonView)
        val typefaceName = values.getString(R.styleable.TypefacedButtonView_titleText)
        var typeface : Typeface? = null
        typefaceName?.let {
            typeface = load(button.context.assets, typefaceName)
        }

        typeface?.let {
                button.typeface = typeface
        }



        values.recycle()
    }
}
