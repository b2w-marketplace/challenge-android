package br.com.andrecouto.alodjinha.util

import android.annotation.SuppressLint
import android.support.annotation.AnimRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import br.com.andrecouto.alodjinha.R

object FragmentUtil {

    @SuppressLint("CommitTransaction")
    private fun ensureTransaction(fragmentManager: FragmentManager): FragmentTransaction {
        return fragmentManager.beginTransaction()
    }

    fun getFragment(fragmentManager: FragmentManager, tag: String): Fragment? {
        return fragmentManager.findFragmentByTag(tag)
    }

    fun getFragment(fragmentManager: FragmentManager, id: Int): Fragment? {
        return fragmentManager.findFragmentById(id)
    }

    private fun attachFragment(
            fragmentTransaction: FragmentTransaction,
            content: Int, fragment: Fragment?
    ) {
        if (fragment != null) {
            if (fragment.isDetached) {
                fragmentTransaction.attach(fragment)
            } else if (!fragment.isAdded) {
                fragmentTransaction.add(content, fragment, fragment.tag)
            }
            fragmentTransaction.commit()
        }
    }

    fun attachFragmentWithAnimation(
            fragmentManager: FragmentManager,
            containerId: Int,
            fragment: Fragment,
            addToBackStack: Boolean
    ) {

        val fragmentTransaction = ensureTransaction(fragmentManager)

        fragmentTransaction.setCustomAnimations(
                R.anim.fadein, R.anim.fadeout, R.anim.fadein,
                R.anim.fadeout
        )

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.tag)
        }
        FragmentUtil.attachFragment(fragmentTransaction, containerId, fragment)
    }

    fun removeFragment(
            fragmentManager: FragmentManager,
            fragment: Fragment, addToBackStack: Boolean
    ) {
        val fragmentTransaction = ensureTransaction(fragmentManager)
        fragmentTransaction.remove(fragment)
        commitTransactions(fragmentTransaction, addToBackStack)
    }

    fun replaceFragmentWithAnimation(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            containerToReplace: Int,
            addToBackStack: Boolean,
            @AnimRes animationEnter: Int,
            @AnimRes animationExit: Int
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.tag)
        }
        fragmentTransaction.setCustomAnimations(animationEnter, animationExit)
        //setFragmentsVisibleHintWithAnimation(fragmentManager, fragment, containerToReplace)

        fragmentTransaction.replace(containerToReplace, fragment).commit()
    }

    fun replaceFragmentWithManyAnimations(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            containerToReplace: Int,
            addToBackStack: Boolean,
            @AnimRes animationEnter: Int,
            @AnimRes animationExit: Int,
            @AnimRes animationPopEnter: Int,
            @AnimRes animationPopExit: Int
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.tag)
        }
        fragmentTransaction.setCustomAnimations(
                animationEnter, animationExit, animationPopEnter,
                animationPopExit
        )
        //setFragmentsVisibleHintWithAnimation(fragmentManager, fragment, containerToReplace)

        fragmentTransaction.replace(containerToReplace, fragment, fragment.tag).commit()
    }

    fun replaceFragmentWithManyAnimations(
            fragmentManager: FragmentManager,
            fragment: Fragment,
            containerToReplace: Int,
            addToBackStack: Boolean,
            tag: String,
            @AnimRes animationEnter: Int,
            @AnimRes animationExit: Int,
            @AnimRes animationPopEnter: Int,
            @AnimRes animationPopExit: Int
    ) {
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (addToBackStack) {
            fragmentTransaction.addToBackStack(tag)
        }
        fragmentTransaction.setCustomAnimations(
                animationEnter, animationExit, animationPopEnter,
                animationPopExit
        )

        fragmentTransaction.replace(containerToReplace, fragment, tag).commit()
    }

    fun replaceFragment(
            fragmentManager: FragmentManager,
            fragment: Fragment, containerToReplace: Int,
            addToBackStack: Boolean
    ) {
        val fragmentTransaction = ensureTransaction(fragmentManager)
        fragmentTransaction.replace(containerToReplace, fragment, fragment.tag)
        //setFragmentsVisibleHintWithAnimation(fragmentManager, fragment, containerToReplace)

        commitTransactions(fragmentTransaction, addToBackStack)
    }

    private fun setFragmentsVisibleHint(
            fragmentManager: FragmentManager?, fragment: Fragment,
            containerToReplace: Int
    ) {
        if (fragmentManager != null) {
            val currentFragment = fragmentManager.findFragmentById(containerToReplace)
            if (currentFragment != null) {
                currentFragment.setUserVisibleHint(false)
            }
            fragment.setUserVisibleHint(true)
        }
    }

    fun replaceFragment(
            fragmentManager: FragmentManager,
            fragment: Fragment, containerToReplace: Int,
            addToBackStack: Boolean, tag: String
    ) {
        val fragmentTransaction = ensureTransaction(fragmentManager)
        fragmentTransaction.replace(containerToReplace, fragment, tag)
        commitTransactions(fragmentTransaction, addToBackStack)

        setFragmentsVisibleHint(fragmentManager, fragment, containerToReplace)
    }

    private fun commitTransactions(
            fragmentTransaction: FragmentTransaction?,
            addToBackStack: Boolean
    ) {
        if (fragmentTransaction != null && !fragmentTransaction.isEmpty()) {
            if (addToBackStack) {
                fragmentTransaction.addToBackStack(null)
            }
            fragmentTransaction.commit()
        }
    }

    fun <T : Fragment> findFragment(
            fragmentManager: FragmentManager,
            tag: String
    ): Fragment? {
        return fragmentManager.findFragmentByTag(tag)
    }

    fun backToFirstFragment(fragmentManager: FragmentManager) {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            val first = fragmentManager.getBackStackEntryAt(0)
            if (!fragmentManager.isDestroyed()) {
                fragmentManager.popBackStackImmediate(
                        first.getId(),
                        FragmentManager.POP_BACK_STACK_INCLUSIVE
                )
            }
        }
    }

    fun getCurrentFragment(fragmentManager: FragmentManager): Fragment? {
        val backStackEntryCount = fragmentManager.getBackStackEntryCount()
        if (backStackEntryCount > 0) {
            val fragmentTag = fragmentManager.getBackStackEntryAt(backStackEntryCount - 1).getName()
            return fragmentManager
                    .findFragmentByTag(fragmentTag)
        }
        return null
    }

}