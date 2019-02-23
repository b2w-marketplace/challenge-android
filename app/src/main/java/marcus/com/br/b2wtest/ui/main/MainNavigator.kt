package marcus.com.br.b2wtest.ui.main

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager

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
}