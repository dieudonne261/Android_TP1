package com.example.nybaiboly

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView

class TestamentAdapter(private val context: Context, private val testamentList: ArrayList<Testament>) :
    RecyclerView.Adapter<TestamentAdapter.TestamentViewHolder>() {
    inner class TestamentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btnTestament: Button = itemView.findViewById(R.id.btnTestament)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestamentViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rowbtn, parent, false)
        return TestamentViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TestamentViewHolder, position: Int) {
        val testament = testamentList[position]
        holder.btnTestament.text = testament.nom_testament
        holder.btnTestament.setOnClickListener {
            val bundle = Bundle()
            bundle.putInt("id_testament", testament.id_testament)
            val livreFragment = LivreFragment()
            livreFragment.arguments = bundle
            DataSample.saveTestament(context,testament.id_testament,0,"")
            val fragmentManager = (holder.itemView.context as AppCompatActivity).supportFragmentManager
            fragmentManager.beginTransaction()
                .replace(R.id.frame_layout, livreFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun getItemCount() = testamentList.size
}
