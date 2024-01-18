package com.example.exammisem

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class Adapter(private val context: Context, private val arrayList: ArrayList<Model>) :
    RecyclerView.Adapter<Adapter.Holder>() {

    private val databaseHelper: DatabaseHelper = DatabaseHelper(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.row, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val model: Model = arrayList[position]
        val id: String = model.id
        val nom: String = model.nom
        val genre: String = model.genre
        val age: String = model.age
        val addTime: String = model.addTimes

        holder.nom_txt.text = nom
        holder.genre_age_txt.text = "$genre - $age ans"
        holder.edbtn.setOnClickListener {
            val intent = Intent(context, EditActivity::class.java)
            intent.putExtra("ID", id)
            intent.putExtra("NOM", nom)
            context.startActivity(intent)
        }
        holder.supbtn.setOnClickListener {
            val builder: AlertDialog.Builder = AlertDialog.Builder(it.context)
            builder.setTitle("Confirmation de suppression")
            builder.setMessage("Êtes-vous sûr de vouloir supprimer cet élément?")

            builder.setPositiveButton("Oui", DialogInterface.OnClickListener { _, _ ->
                databaseHelper.deleteInfo(id)
                Toast.makeText(context, "Suppression réussie...", Toast.LENGTH_LONG).show()
                (context as MainActivity).onResume()
            })

            builder.setNegativeButton("Non", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            builder.create().show()
        }
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nom_txt: TextView = itemView.findViewById(R.id.noma_txt)
        val genre_age_txt: TextView = itemView.findViewById(R.id.genre_age)
        val edbtn: ImageButton = itemView.findViewById(R.id.editBtn)
        val supbtn: ImageButton = itemView.findViewById(R.id.supBtn)
    }
}
