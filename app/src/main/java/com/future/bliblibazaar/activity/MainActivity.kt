package com.future.bliblibazaar.activity

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.view.BazaarFragment
import com.future.bliblibazaar.cart.view.CartBottomSheetFragment
import com.future.bliblibazaar.databinding.ActivityMainBinding
import com.future.bliblibazaar.fragment.HistoryFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : BaseActivity() {
    private var cartFragment: CartBottomSheetFragment? = null
    private lateinit var mBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initToolbar()

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_bazaar -> {
                    initFragmentStateLoss(BazaarFragment(), "BAZAAR_FRAGMENT", R.id.fl_countainer)

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_cart -> {
                    cartFragment?.let { cart ->
                        showDialogFragment(cart, "CART_FRAGMENT")
                    } ?: run {
                        cartFragment = CartBottomSheetFragment.newInstance("")
                        showDialogFragment(cartFragment!!, "CART_FRAGMENT")
                    }

                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_history -> {
                    initFragmentStateLoss(HistoryFragment(),"HISTORY_FRAGMENT", R.id.fl_countainer)

                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

        initFragmentStateLoss(BazaarFragment(), "BAZAAR_FRAGMENT", R.id.fl_countainer)
    }

    private fun initToolbar() {
        mBinding.customToolbar.toolbar.title = getString(R.string.app_name)
        mBinding.customToolbar.toolbar.navigationIcon?.setVisible(false, false)
        mBinding.customToolbar.toolbar.setNavigationOnClickListener {
            //No Implementation required
        }
    }

}
