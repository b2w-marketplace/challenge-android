package marcus.com.br.b2wtest.ui.main

import android.content.Context
import android.content.Intent
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import marcus.com.br.b2wtest.model.data.ProductData
import marcus.com.br.b2wtest.ui.productdetail.ProductDetailActivity

object MainNavigator {

    fun navigateToFragment(
        manager: FragmentManager?, fragment: Fragment,
        @IdRes viewId: Int, tag: String
    ) {
        manager?.let {
            manager.beginTransaction()
                .replace(viewId, fragment, tag)
                .commit()
        }
    }

    fun navigateToProductDetailActivity(context: Context, productData: ProductData) {
        val intent = Intent(context, ProductDetailActivity::class.java)
        intent.putExtra("productData", productData)
        context.startActivity(intent)
    }
}