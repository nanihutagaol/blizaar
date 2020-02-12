package com.future.bliblibazaar.order.view

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.future.bliblibazaar.R
import com.future.bliblibazaar.activity.BaseActivity
import com.future.bliblibazaar.cart.adapter.CartDetailAdapter
import com.future.bliblibazaar.cart.model.CartItemDto
import com.future.bliblibazaar.databinding.ActivityOrderDetailBinding
import com.future.bliblibazaar.order.viewmodel.OrderDetailViewModel
import java.text.NumberFormat

class OrderDetailActivity : BaseActivity() {
    private lateinit var mBinding: ActivityOrderDetailBinding
    private val orderDetailViewModel = OrderDetailViewModel()
    private var barcodeFragment: BarcodeBottomSheetFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail)

        initToolbar()

        val intent = intent
        val bundle: Bundle? = intent.getBundleExtra("ORDER_BUNDLE")
        val orderId = intent.getStringExtra("ORDER_ID")

        if (bundle != null) {
            val transactionNo = bundle.getString("ORDER_ID")
            val transactionDate = bundle.getString("ORDER_DATE")
            val orderStatus = bundle.getString("ORDER_STATUS")
            val items = bundle.getParcelableArrayList<CartItemDto>("ORDER_ITEMS")
            val totalPrice = bundle.getString("ORDER_TOTAL_PRICE")

            mBinding.tvOrderNumber.text = transactionNo
            mBinding.tvTransactionDate.text = transactionDate
            mBinding.tvOrderStatus.text = orderStatus
            mBinding.rvOrderItems.adapter = CartDetailAdapter(items!!.toList())
            mBinding.rvOrderItems.layoutManager = LinearLayoutManager(this)
            mBinding.tvOrderTotalPrice.text = "Rp. ${totalPrice}"

            mBinding.btGenerateBarcode.setOnClickListener{
                barcodeFragment?.let { barcode ->
                    showDialogFragment(barcode, "BARCODE_FRAGMENT")
                } ?: run {
                    barcodeFragment = BarcodeBottomSheetFragment.newInstance(orderId)
                    showDialogFragment(barcodeFragment!!, "BARCODE_FRAGMENT")
                }
            }
        } else {
            orderDetailViewModel.getOrder(orderId)
            orderDetailViewModel.orderDetailLiveData.observe(this, Observer {
                if (it != null) {
                    var totalPrice = NumberFormat.getInstance().format(it.orderTotalPrice)

                    mBinding.tvOrderNumber.text = it.orderId
                    mBinding.tvTransactionDate.text = it.orderDate
                    mBinding.tvOrderStatus.text = it.orderStatus
                    mBinding.rvOrderItems.adapter = CartDetailAdapter(it.orderItems!!.toList())
                    mBinding.rvOrderItems.layoutManager = LinearLayoutManager(this)
                    mBinding.tvOrderTotalPrice.text = "Rp. ${totalPrice}"
                }
            })

            mBinding.btGenerateBarcode.setOnClickListener{
                barcodeFragment?.let { cart ->
                    showDialogFragment(cart, "BARCODE_FRAGMENT")
                } ?: run {
                    barcodeFragment = BarcodeBottomSheetFragment.newInstance(orderId)
                    showDialogFragment(barcodeFragment!!, "BARCODE_FRAGMENT")
                }
            }
        }
    }

    private fun initToolbar() {
        mBinding.orderDetailToolbar.toolbar.title = getString(R.string.app_name)
        mBinding.orderDetailToolbar.toolbar.navigationIcon?.setVisible(false, false)
        mBinding.orderDetailToolbar.toolbar.setNavigationOnClickListener {
            finish()
        }
    }

}