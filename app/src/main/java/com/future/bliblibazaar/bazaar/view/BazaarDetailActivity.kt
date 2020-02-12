package com.future.bliblibazaar.bazaar.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.future.bliblibazaar.R
import com.future.bliblibazaar.activity.BaseActivity
import com.future.bliblibazaar.bazaar.adapter.BazaarDetailAdapter
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import com.future.bliblibazaar.bazaar.viewmodel.BazaarDetailViewModel
import com.future.bliblibazaar.databinding.ActivityBazaarDetailBinding
import kotlinx.coroutines.launch

class BazaarDetailActivity : BaseActivity(), BazaarDetailAdapter.IBazaarDetailCommunicator {
    private lateinit var mBinding: ActivityBazaarDetailBinding
    private var bazaarDetailViewModel: BazaarDetailViewModel = BazaarDetailViewModel()
    private var bazaarItems = ArrayList<BazaarItemDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bazaar_detail)
        val bazaarId = intent.getLongExtra("BAZAAR_ID", 0)

        initToolbar()

        mBinding.rvProductList.adapter = BazaarDetailAdapter(this, bazaarItems)

        bazaarDetailViewModel.getBazaarDetail(bazaarId)
        bazaarDetailViewModel.bazaarDetailLiveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                bazaarItems.addAll(it)
                mBinding.rvProductList.adapter?.notifyDataSetChanged()
            }
        })

    }

    private fun initToolbar() {
        mBinding.bazaarDetailCustomToolbar.toolbar.title = getString(R.string.app_name)
        mBinding.bazaarDetailCustomToolbar.toolbar.navigationIcon?.setVisible(false, false)
        mBinding.bazaarDetailCustomToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

    override fun book(productId: String) {
        bazaarDetailViewModel.viewModelScope.launch {
            if(!bazaarDetailViewModel.addItemToCart(productId)){
                Toast.makeText(this@BazaarDetailActivity, "Out of stock", Toast.LENGTH_SHORT).show()
            }
        }

        bazaarDetailViewModel.addItemLiveData.observe(this, Observer {
            if (it) {
                Toast.makeText(this, "Item added to cart", Toast.LENGTH_SHORT).show()
            }
        })
    }

}