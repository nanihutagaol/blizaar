package com.future.bliblibazaar.bazaar.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.future.bliblibazaar.R
import com.future.bliblibazaar.bazaar.model.BazaarDto
import com.future.bliblibazaar.bazaar.view.BazaarDetailActivity

class BazaarAdapter(private val bazaars: ArrayList<BazaarDto>) :
    RecyclerView.Adapter<BazaarAdapter.BazaarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BazaarViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.bazaar_list_item, parent, false)

        return BazaarViewHolder(view)
    }

    override fun onBindViewHolder(holder: BazaarViewHolder, position: Int) {
        holder.tvBazaarName.text = bazaars[position].name

        holder.btBazaarDetail.setOnClickListener {
            val intent = Intent(it.context, BazaarDetailActivity::class.java)
            intent.putExtra("BAZAAR_ID", bazaars[position].id)

            it.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return bazaars.size
    }

    inner class BazaarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBazaarName = itemView.findViewById<TextView>(R.id.tv_bazaar_name)
        val tvBazaarEndDate = itemView.findViewById<TextView>(R.id.tv_bazaar_end_date)
        val btBazaarDetail = itemView.findViewById<ImageButton>(R.id.bt_bazaar_detail)
    }

}