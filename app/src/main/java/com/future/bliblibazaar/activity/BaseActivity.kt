package com.future.bliblibazaar.activity

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment

open class BaseActivity : AppCompatActivity() {

    protected fun showDialogFragment(dialogFragment: DialogFragment, tag: String = "") {
        // DialogFragment.show() will take care of adding the fragment
        // in a transaction. We also want to remove any currently showing
        // dialog, so make our own transaction and take care of that here.
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        if (!tag.isEmpty()) {
            val prevFragment = supportFragmentManager.findFragmentByTag(tag)
            if (prevFragment != null) {
                fragmentTransaction.remove(prevFragment)
            }
        }
        fragmentTransaction.addToBackStack(null)

        // Create and show the dialog.
        if (!isFinishing) {
            dialogFragment.show(fragmentTransaction, tag)
        }
    }

    protected fun initFragmentStateLoss(f: Fragment, tag: String = "", id: Int) {
        try {
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            val fragment = supportFragmentManager.findFragmentByTag(tag)
            if (fragment != null) {
                val removeFragmentTransaction = supportFragmentManager.beginTransaction()
                removeFragmentTransaction.remove(fragment)
                removeFragmentTransaction.commitNowAllowingStateLoss()
            }
            fragmentTransaction.add(id, f, tag)
            fragmentTransaction.commitAllowingStateLoss()
        } catch (exception: Exception) {
        }
    }

}