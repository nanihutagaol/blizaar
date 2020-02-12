package com.future.bliblibazaar.cart.view

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.future.bliblibazaar.R
import com.future.bliblibazaar.cart.adapter.CartDetailAdapter
import com.future.bliblibazaar.cart.model.CartItemDto
import com.future.bliblibazaar.cart.viewmodel.CartDetailViewModel
import com.future.bliblibazaar.databinding.FragmentBottomSheetDialogBinding
import com.future.bliblibazaar.order.view.OrderDetailActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.launch
import java.text.NumberFormat

class CartBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentBottomSheetDialogBinding
    private val cartDetailViewModel = CartDetailViewModel()
    private var cartItems = ArrayList<CartItemDto>()
    private val cartDetailAdapter = CartDetailAdapter(cartItems)

    companion object {
        fun newInstance(): CartBottomSheetFragment {
            return CartBottomSheetFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_bottom_sheet_dialog,
            container,
            false
        )
        mBinding.rvCartItem.adapter = cartDetailAdapter
        mBinding.rvCartItem.layoutManager = LinearLayoutManager(context)

        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var totalPrice = 0
        var formatter = NumberFormat.getInstance()

        cartDetailViewModel.viewModelScope.launch {
            if (!cartDetailViewModel.getCartItem()) {
                Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
            }
        }
        cartDetailViewModel.cartItemsLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                cartDetailAdapter.setCartItems(it)
                for (item in it) {
                    totalPrice += item.cartItemOfferPrice.toInt()
                }
                mBinding.tvTotalCartPrice.text = formatter.format(totalPrice)
                mBinding.btCheckout.visibility = View.VISIBLE
            } else {
                mBinding.btCheckout.visibility = View.GONE
            }
        })

        mBinding.btCheckout.setOnClickListener {
            cartDetailViewModel.viewModelScope.launch {
                if (!cartDetailViewModel.checkout()) {
                    Toast.makeText(context, "Checkout error", Toast.LENGTH_SHORT).show()
                }
            }
        }

        cartDetailViewModel.orderLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                var intent = Intent(context, OrderDetailActivity::class.java)
                var bundle = Bundle()
                bundle.putString("ORDER_ID", it.orderId)
                bundle.putString("ORDER_DATE", it.orderDate)
                bundle.putParcelableArrayList("ORDER_ITEMS", ArrayList(it.orderItems))
                bundle.putString("ORDER_STATUS", it.orderStatus)
                bundle.putString("ORDER_TOTAL_PRICE", formatter.format(it.orderTotalPrice))

                intent.putExtra("ORDER_BUNDLE", bundle)
                startActivity(intent)
            }
        })
    }

}