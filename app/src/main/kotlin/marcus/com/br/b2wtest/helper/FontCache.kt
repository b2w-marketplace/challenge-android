package marcus.com.br.b2wtest.helper

import android.content.Context
import android.graphics.Typeface
import java.util.*

class FontCache {
    private val fontMap = HashMap<String, Typeface>()
    fun getFont(context: Context, fontName: String): Typeface? {
        return if (fontMap.containsKey(fontName)) {
            fontMap[fontName]
        } else {
            val tf = Typeface.createFromAsset(context.assets, fontName)
            fontMap[fontName] = tf
            tf
        }
    }
}