package com.dimalahmad.foodheroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimalahmad.foodheroes.R
import com.dimalahmad.foodheroes.models.Donation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AdminFragment : Fragment() {

    private lateinit var donationRecyclerView: RecyclerView
    private lateinit var donationAdapter: DonationAdminAdapter
    private var donationList: MutableList<Donation> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_admin, container, false)

        // Initialize RecyclerView
        donationRecyclerView = view.findViewById(R.id.rvAdminDonations)
        donationRecyclerView.layoutManager = LinearLayoutManager(context)
        donationAdapter = DonationAdminAdapter(donationList) { donation, action ->
            handleDonationAction(donation, action)
        }
        donationRecyclerView.adapter = donationAdapter

        // Load Donations
        loadDonations()

        return view
    }

    private fun loadDonations() {
        RetrofitClient.instance.getDonations().enqueue(object : Callback<List<Donation>> {
            override fun onResponse(call: Call<List<Donation>>, response: Response<List<Donation>>) {
                if (response.isSuccessful) {
                    donationList.clear()
                    donationList.addAll(response.body()!!)
                    donationAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<Donation>>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun handleDonationAction(donation: Donation, action: String) {
        when (action) {
            "update_status" -> updateDonationStatus(donation)
            "delete" -> deleteDonation(donation)
        }
    }

    private fun updateDonationStatus(donation: Donation) {
        donation.status = "In Progress"
        RetrofitClient.instance.createDonation(donation).enqueue(object : Callback<Donation> {
            override fun onResponse(call: Call<Donation>, response: Response<Donation>) {
                if (response.isSuccessful) {
                    donationAdapter.notifyDataSetChanged()
                    Toast.makeText(context, "Status updated", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Donation>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun deleteDonation(donation: Donation) {
        // Handle deletion logic here
    }
}
