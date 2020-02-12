package com.future.bliblibazaar.order.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.future.bliblibazaar.R
import com.future.bliblibazaar.databinding.FragmentBarcodeBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.google.zxing.WriterException
import com.google.zxing.common.BitMatrix
import com.journeyapps.barcodescanner.BarcodeEncoder

class BarcodeBottomSheetFragment : BottomSheetDialogFragment() {
    private lateinit var mBinding: FragmentBarcodeBottomSheetDialogBinding

    private val orderId: String
        get() = arguments?.getString(BarcodeBottomSheetFragment.ORDER_ID_KEY) ?: ""

    companion object {
        private val ORDER_ID_KEY = "order_id"

        fun newInstance(id: String): BarcodeBottomSheetFragment {
            return BarcodeBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ORDER_ID_KEY, id)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_barcode_bottom_sheet_dialog,
            container,
            false
        )

        val multiFormatWriter = MultiFormatWriter()

        try {
            val bitMatrix: BitMatrix =
                multiFormatWriter.encode(orderId, BarcodeFormat.QR_CODE, 150, 150)
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.createBitmap(bitMatrix)

            mBinding.ivOrderBarcode.setImageBitmap(bitmap)
        } catch (ex: WriterException) {
            ex.printStackTrace()
        }

        return mBinding.root
    }

}