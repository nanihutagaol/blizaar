package com.future.bliblibazaar.cart.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.future.bliblibazaar.R
import com.future.bliblibazaar.databinding.FragmentBottomSheetDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class CartBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var mBinding: FragmentBottomSheetDialogBinding
    private val id: String
        get() = arguments?.getString(ID_KEY) ?: ""

    companion object {
        private val ID_KEY = "id"

        fun newInstance(id: String): CartBottomSheetFragment {
            return CartBottomSheetFragment().apply {
                arguments = Bundle().apply {
                    putString(ID_KEY, id)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        DataBindingUtil.inflate<FragmentBottomSheetDialogBinding>(
            inflater,
            R.layout.fragment_bottom_sheet_dialog,
            container,
            false
        ).root

    fun asik() {
        id
        //TODO geerate bacode
    }

}