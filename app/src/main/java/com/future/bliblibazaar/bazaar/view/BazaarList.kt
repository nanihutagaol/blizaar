package com.future.bliblibazaar.bazaar.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.adapter.BazaarAdapter
import com.future.bliblibazaar.bazaar.viewmodel.BazaarViewModel
import com.future.bliblibazaar.databinding.BazaarListBinding

class BazaarList : AppCompatActivity() {
    private lateinit var mBinding: BazaarListBinding
    private val bazaarViewModel = BazaarViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mBinding = DataBindingUtil.setContentView(this, R.layout.bazaar_list)
//
//        bazaarViewModel.getActiveBazaars()
//
//        bazaarViewModel.bazaarsLiveData.observe(this, Observer {
//            if(it.data.isNotEmpty()) {
//                val layoutManager = LinearLayoutManager(this)
//                layoutManager.orientation = LinearLayoutManager.VERTICAL
//
//                mBinding.rvBazaarList.adapter = BazaarAdapter(ArrayList(it.data))
//                mBinding.rvBazaarList.layoutManager = layoutManager
//            } else {
//                //TODO throw error here
//            }
//        })
    }

}