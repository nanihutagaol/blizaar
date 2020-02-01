package com.future.bliblibazaar.bazaar.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.adapter.BazaarAdapter
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.viewmodel.BazaarViewModel
import com.future.bliblibazaar.databinding.BazaarListBinding
import com.future.bliblibazaar.databinding.FragmentBazaarBinding

class BazaarFragment : Fragment() {
    private lateinit var mBinding: FragmentBazaarBinding
    private val bazaarViewModel = BazaarViewModel()
    private var bazaars = ArrayList<BazaarDto>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bazaar, container, false)
        mBinding.rvBazaarList.adapter = BazaarAdapter(bazaars)
        mBinding.rvBazaarList.layoutManager = LinearLayoutManager(context)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bazaarViewModel.getActiveBazaars()
        bazaarViewModel.bazaarsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                bazaars.addAll(it)
                mBinding.rvBazaarList.adapter?.notifyDataSetChanged()
            }
        })
    }
}
