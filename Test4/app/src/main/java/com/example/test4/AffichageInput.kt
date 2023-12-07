package com.example.test4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class AffichageInput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_affichage_input)
        val intent = intent
        val element = intent.getStringExtra("element")
        val textview = findViewById<TextView>(R.id.textView)
        textview.text = element
    }
}