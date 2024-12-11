package com.dimalahmad.foodheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimalahmad.foodheroes.R
import com.dimalahmad.foodheroes.models.Donation

class DonationAdminAdapter(
    private val donations: List<Donation>,
    private val onAction: (Donation, String) -> Unit
) : RecyclerView.Adapter<DonationAdminAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.tvAdminMenuName)
        val capacity: TextView = view.findViewById(R.id.tvAdminCapacity)
        val status: TextView = view.findViewById(R.id.tvAdminStatus)
        val btnUpdateStatus: Button = view.findViewById(R.id.btnUpdateStatus)
        val btnDelete: Button = view.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_admin_donation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donation = donations[position]
        holder.menuName.text = donation.menuName
        holder.capacity.text = "Capacity: ${donation.capacity}"
        holder.status.text = "Status: ${donation.status}"

        holder.btnUpdateStatus.setOnClickListener {
            onAction(donation, "update_status")
        }

        holder.btnDelete.setOnClickListener {
            onAction(donation, "delete")
        }
    }

    override fun getItemCount() = donations.size
}
