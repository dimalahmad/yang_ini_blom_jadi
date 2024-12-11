package com.dimalahmad.foodheroes.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dimalahmad.foodheroes.R
import com.dimalahmad.foodheroes.models.Donation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PartnerFragment : Fragment() {

    private lateinit var donationAdapter: DonationAdapter
    private lateinit var donationRecyclerView: RecyclerView
    private var donationList: MutableList<Donation> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_partner, container, false)

        // Initialize RecyclerView
        donationRecyclerView = view.findViewById(R.id.rvDonations)
        donationRecyclerView.layoutManager = LinearLayoutManager(context)
        donationAdapter = DonationAdapter(donationList)
        donationRecyclerView.adapter = donationAdapter

        // Add Donation Button
        val btnAddDonation = view.findViewById<Button>(R.id.btnAddDonation)
        btnAddDonation.setOnClickListener {
            addDonation(view)
        }

        // Load Donations
        loadDonations()

        return view
    }

    private fun addDonation(view: View) {
        val menuName = view.findViewById<EditText>(R.id.etMenuName).text.toString()
        val capacity = view.findViewById<EditText>(R.id.etCapacity).text.toString().toIntOrNull()

        if (menuName.isEmpty() || capacity == null) {
            Toast.makeText(context, "Please fill all fields", Toast.LENGTH_SHORT).show()
            return
        }

        val newDonation = Donation(
            id = 0,
            menuName = menuName,
            capacity = capacity,
            status = "Waiting for pickup"
        )

        RetrofitClient.instance.createDonation(newDonation).enqueue(object : Callback<Donation> {
            override fun onResponse(call: Call<Donation>, response: Response<Donation>) {
                if (response.isSuccessful) {
                    donationList.add(response.body()!!)
                    donationAdapter.notifyDataSetChanged()
                    Toast.makeText(context, "Donation added", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to add donation", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<Donation>, t: Throwable) {
                Toast.makeText(context, "Error: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
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
}
