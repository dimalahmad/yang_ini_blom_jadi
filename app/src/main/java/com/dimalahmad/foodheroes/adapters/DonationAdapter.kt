package com.dimalahmad.foodheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimalahmad.foodheroes.R
import com.dimalahmad.foodheroes.models.Donation

class DonationAdapter(private val donations: List<Donation>) : RecyclerView.Adapter<DonationAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.tvMenuName)
        val capacity: TextView = view.findViewById(R.id.tvCapacity)
        val status: TextView = view.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_donation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donation = donations[position]
        holder.menuName.text = donation.menuName
        holder.capacity.text = "Capacity: ${donation.capacity}"
        holder.status.text = "Status: ${donation.status}"
    }

    override fun getItemCount() = donations.size
}
