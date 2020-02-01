package com.future.bliblibazaar.bazaar.view

import android.os.Bundle
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.adapter.BazaarDetailAdapter
import com.future.bliblibazaar.bazaar.model.BazaarItemDto
import com.future.bliblibazaar.bazaar.viewmodel.BazaarDetailViewModel
import com.future.bliblibazaar.databinding.ActivityBazaarDetailBinding

class BazaarDetailActivity: AppCompatActivity(), BazaarDetailAdapter.IBazaarDetailCommunicator {
    private lateinit var mBinding: ActivityBazaarDetailBinding
    private var bazaarDetailViewModel: BazaarDetailViewModel = BazaarDetailViewModel()
    private var bazaarItems = ArrayList<BazaarItemDto>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_bazaar_detail)
        val bazaarId = intent.getLongExtra("BAZAAR_ID", 0)

        mBinding.rvProductList.adapter = BazaarDetailAdapter(this,bazaarItems)

        bazaarDetailViewModel.getBazaarDetail(bazaarId)
        bazaarDetailViewModel.bazaarDetailLiveData.observe(this, Observer {
            if(it.isNotEmpty()) {
                bazaarItems.addAll(it)
                mBinding.rvProductList.adapter?.notifyDataSetChanged()
            }
        })

    }

    override fun book(id: String) {
        Toast.makeText(this, id, Toast.LENGTH_SHORT).show()
    }

}