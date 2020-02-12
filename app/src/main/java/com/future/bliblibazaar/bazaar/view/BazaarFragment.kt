package com.future.bliblibazaar.bazaar.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.adapter.BazaarAdapter
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.viewmodel.BazaarViewModel
import com.future.bliblibazaar.databinding.FragmentBazaarBinding
import com.future.bliblibazaar.order.adapter.OrderAdapter
import com.future.bliblibazaar.order.model.OrderDto
import kotlinx.coroutines.launch

class BazaarFragment : Fragment() {
    private lateinit var mBinding: FragmentBazaarBinding
    private val bazaarViewModel = BazaarViewModel()
    private var bazaars = ArrayList<BazaarDto>()
    private var orders = ArrayList<OrderDto>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_bazaar, container, false)
        mBinding.rvBazaarList.adapter = BazaarAdapter(bazaars)
        mBinding.rvBazaarList.layoutManager = LinearLayoutManager(context)
        mBinding.rvOrderList.adapter = OrderAdapter(orders)
        mBinding.rvOrderList.layoutManager = LinearLayoutManager(context)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bazaarViewModel.viewModelScope.launch {
            if (!bazaarViewModel.getActiveBazaars()) {
                Toast.makeText(context, "Failed to fetch bazaar data", Toast.LENGTH_SHORT).show()
            }
        }

        bazaarViewModel.bazaarsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                bazaars.addAll(it)
                mBinding.rvBazaarList.adapter?.notifyDataSetChanged()
            }
        })

        bazaarViewModel.viewModelScope.launch {
            if (!bazaarViewModel.getOrder()) {
                Toast.makeText(context, "Failed to fetch order data", Toast.LENGTH_SHORT).show()
            }
        }

        bazaarViewModel.orderLiveData.observe(viewLifecycleOwner, Observer {
            if (it.orders.isNotEmpty()) {
                orders.addAll(it.orders)
                mBinding.rvOrderList.adapter?.notifyDataSetChanged()
            }
        })
    }
}
