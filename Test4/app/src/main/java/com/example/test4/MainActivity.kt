package com.example.test4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.util.Locale

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val secondAction = findViewById<Button>(R.id.button)
        secondAction.setOnClickListener{
            val intent = Intent(this,AffichageInput::class.java)
            val nom = findViewById<EditText>(R.id.editTextText3).text.toString()
            val prenom = findViewById<EditText>(R.id.editTextText2).text.toString()
            val email = findViewById<EditText>(R.id.editTextTextEmailAddress).text.toString()
            val phone = findViewById<EditText>(R.id.editTextPhone).text.toString()
            intent.putExtra("element", "Bienvenue,\n${nom.uppercase()}  ${prenom.replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(
                    Locale.getDefault()
                ) else it.toString()
            }} \nEmail : $email \nPhone : $phone")
            if (nom.isNullOrEmpty()){
                Toast.makeText(this,"Le nom ne peut pas etre vide",Toast.LENGTH_SHORT).show()
            }
            else{
                startActivity(intent)
            }

        }
    }
}