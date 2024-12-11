package com.dimalahmad.foodheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dimalahmad.foodheroes.R
import com.dimalahmad.foodheroes.models.Donation

class DonationUserAdapter(
    private val donations: List<Donation>,
    private val onRegister: (Donation) -> Unit
) : RecyclerView.Adapter<DonationUserAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val menuName: TextView = view.findViewById(R.id.tvUserMenuName)
        val capacity: TextView = view.findViewById(R.id.tvUserCapacity)
        val btnRegister: Button = view.findViewById(R.id.btnRegister)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_donation, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val donation = donations[position]
        holder.menuName.text = donation.menuName
        holder.capacity.text = "Capacity: ${donation.capacity}"
        holder.btnRegister.setOnClickListener {
            onRegister(donation)
        }
    }

    override fun getItemCount() = donations.size
}
