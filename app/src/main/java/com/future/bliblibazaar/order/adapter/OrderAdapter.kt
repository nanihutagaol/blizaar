package com.future.bliblibazaar.order.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.future.bliblibazaar.R
import com.future.bliblibazaar.order.model.OrderDto
import com.future.bliblibazaar.order.view.OrderDetailActivity
import java.text.NumberFormat

class OrderAdapter(val orders: List<OrderDto>) :
    RecyclerView.Adapter<OrderAdapter.OrderItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.order_list_item, parent, false)

        return OrderItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return orders.size
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val formatter = NumberFormat.getInstance()
        val orderId = orders[position].orderId
        val totalItem = orders[position].orderTotalItem.toString()
        val totalPrice = orders[position].orderTotalPrice

        holder.transactionNo.text = orderId
        holder.totalItem.text = "Total Item: ${totalItem}"
        holder.totalPrice.text = "Total Price: Rp. ${formatter.format(totalPrice)}"
        holder.detailButton.setOnClickListener {
            val intent = Intent(it.context, OrderDetailActivity::class.java)
            intent.putExtra("ORDER_ID", orderId)

            it.context.startActivity(intent)
        }
    }

    inner class OrderItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val transactionNo = itemView.findViewById<TextView>(R.id.tv_order_id)
        val totalItem = itemView.findViewById<TextView>(R.id.tv_total_item)
        val totalPrice = itemView.findViewById<TextView>(R.id.tv_total_price)
        val detailButton = itemView.findViewById<ImageButton>(R.id.bt_order_detail)
    }

}
