package com.future.bliblibazaar.bazaar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.model.BazaarItemDto

class BazaarDetailAdapter(
    private val iCommunicator: IBazaarDetailCommunicator,
    private val bazaarItems: List<BazaarItemDto>
) :
    RecyclerView.Adapter<BazaarDetailAdapter.BazaarProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BazaarProductViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.bazaar_detail_item, parent, false)

        return BazaarProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bazaarItems.size
    }

    override fun onBindViewHolder(holder: BazaarProductViewHolder, position: Int) {
        holder.productName.text = bazaarItems[position].productName
        holder.productReturnReason.text = bazaarItems[position].productReturnReason
        holder.productCondition.text = bazaarItems[position].productCondition
        holder.productPrice.text = "Rp. ${bazaarItems[position].productOfferPrice}"
        holder.bookProduct.setOnClickListener {
            iCommunicator.book(bazaarItems[holder.adapterPosition].productId)
        }
    }


    inner class BazaarProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName = itemView.findViewById<TextView>(R.id.tv_product_name)
        val productReturnReason = itemView.findViewById<TextView>(R.id.tv_product_return_reason)
        val productCondition = itemView.findViewById<TextView>(R.id.tv_product_condition)
        val productPrice = itemView.findViewById<TextView>(R.id.tv_product_price)
        val bookProduct = itemView.findViewById<Button>(R.id.bt_book_product)
    }

    interface IBazaarDetailCommunicator {
        fun book(id: String)
    }

}