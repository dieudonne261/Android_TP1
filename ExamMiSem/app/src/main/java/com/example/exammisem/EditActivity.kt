package com.example.exammisem


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class EditActivity : AppCompatActivity() {

    private lateinit var pNom: EditText
    private lateinit var tvDate: TextView
    private lateinit var genreSelect: Spinner
    private lateinit var saveBtn: Button
    private lateinit var id: String
    private lateinit var nom: String
    private lateinit var genre: String
    private lateinit var age: String
    private lateinit var timesp: String
    private var editMode = false
    private lateinit var databaseHelper: DatabaseHelper
    private val calendar: Calendar = Calendar.getInstance()
    private val year: Int = calendar.get(Calendar.YEAR)
    private val month: Int = calendar.get(Calendar.MONTH)
    private val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    private var setListener: DatePickerDialog.OnDateSetListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        pNom = findViewById(R.id.nom)
        saveBtn = findViewById(R.id.buttonsave)
        tvDate = findViewById(R.id.Ddn)
        genreSelect = findViewById(R.id.gender)

        val intent = intent
        editMode = intent.getBooleanExtra("editMode", editMode)
        id = intent.getStringExtra("ID") ?: ""
        nom = intent.getStringExtra("NOM") ?: ""
        genre = intent.getStringExtra("GENRE") ?: ""
        age = intent.getStringExtra("AGE") ?: ""
        pNom.setText(nom)

        databaseHelper = DatabaseHelper(this)

        tvDate.setOnClickListener {
            val datePickerDialog = DatePickerDialog(
                this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                setListener, year, month, day
            )
            datePickerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            datePickerDialog.show()
        }

        setListener = DatePickerDialog.OnDateSetListener { view: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->
            val month = month + 1
            val date = "$dayOfMonth/$month/$year"
            tvDate.text = date
        }

        saveBtn.setOnClickListener {
            getData()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun getData() {
        nom = pNom.text.toString().trim()
        genre = genreSelect.selectedItem.toString()
        age = tvDate.text.toString().trim()
        timesp = System.currentTimeMillis().toString()

        if (nom.isEmpty()) {
            Toast.makeText(this, "Le nom entré est vide", Toast.LENGTH_LONG).show()
        } else if (obtenirAge(age) == 0) {
            Toast.makeText(this, "Date de naissance invalide", Toast.LENGTH_LONG).show()
        } else {
            databaseHelper.updateInfo(
                id,
                nom,
                genre,
                obtenirAge(age).toString(),
                timesp,
                timesp
            )
            Toast.makeText(this, "Modification réussie ...", Toast.LENGTH_LONG).show()
            startActivity(Intent(this@EditActivity, MainActivity::class.java))
        }
    }

    private fun obtenirAge(date: String): Int {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        try {
            val dateNaissance = sdf.parse(date)
            val dob = Calendar.getInstance()
            dob.time = dateNaissance
            val currentDate = Calendar.getInstance()
            if (currentDate.before(dob)) {
                println("Date future, l'âge ne peut pas être calculé.")
                return 0
            }
            var age = currentDate[Calendar.YEAR] - dob[Calendar.YEAR]
            if (currentDate[Calendar.DAY_OF_YEAR] < dob[Calendar.DAY_OF_YEAR]) {
                age--
            }
            if (age < 1) {
                return 0
            }
            return age
        } catch (e: ParseException) {
            return 0
        }
    }
}
