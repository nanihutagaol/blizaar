package com.future.bliblibazaar.cart.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.future.bliblibazaar.R
import com.future.bliblibazaar.cart.model.CartItemDto

class CartDetailAdapter(private var cartItems: List<CartItemDto>) :
    RecyclerView.Adapter<CartDetailAdapter.CartItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cart_list_item, parent, false)

        return CartItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cartItems.size
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.itemName.text = cartItems[position].cartItemName
        holder.itemReturnReason.text = cartItems[position].cartReturnReason
        holder.itemCondition.text = cartItems[position].cartCondition
        holder.itemPrice.text = "Rp. ${cartItems[position].cartItemOfferPrice}"
    }

    inner class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName = itemView.findViewById<TextView>(R.id.tv_item_name)
        val itemReturnReason = itemView.findViewById<TextView>(R.id.tv_item_return_reason)
        val itemCondition = itemView.findViewById<TextView>(R.id.tv_item_condition)
        val itemPrice = itemView.findViewById<TextView>(R.id.tv_item_price)
    }

    fun setCartItems(cartItems: List<CartItemDto>) {
        this.cartItems = cartItems
        notifyDataSetChanged()
    }

}