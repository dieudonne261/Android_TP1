package com.example.composant

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.Switch
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val swi = findViewById<Switch>(R.id.switch2)
        swi.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                findViewById<Button>(R.id.button).isEnabled = false
                findViewById<EditText>(R.id.editTextTextPassword).isEnabled = false
                findViewById<CheckBox>(R.id.checkBox).isEnabled = false
            }
            else {
                findViewById<Button>(R.id.button).isEnabled = true
                findViewById<EditText>(R.id.editTextTextPassword).isEnabled = true
                findViewById<CheckBox>(R.id.checkBox).isEnabled = true
            }
        }

        val checkBox = findViewById<CheckBox>(R.id.checkBox)
        val editTextPassword = findViewById<EditText>(R.id.editTextTextPassword)
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                editTextPassword.visibility = View.GONE
                checkBox.text = "Voir"

            } else {
                editTextPassword.visibility = View.VISIBLE
                checkBox.text = "Caché"
            }
        }

        val secondAction = findViewById<Button>(R.id.button)
        secondAction.setOnClickListener {

            if (editTextPassword.text.isNullOrEmpty()) {

                Toast.makeText(this, "Le mot de passe ne peut pas être vide", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Votre mot de passe : ${editTextPassword.text}", Toast.LENGTH_SHORT).show()
            }
        }

        val progressBar = findViewById<ProgressBar>(R.id.progressBar7)
        Thread {
            var i = 0
            while (i <= 100) {
                runOnUiThread {
                    progressBar.progress = i
                }
                Thread.sleep(50)
                if (i == 100) {
                    i = 0
                } else {
                    i++
                }
            }
        }.start()

        val bgColors = arrayOf("#51E68D", "#A913EA", "#27B0FA")
        var currentColorIndex = 0

        findViewById<ImageButton>(R.id.imageButton3).setOnClickListener {
            currentColorIndex = (currentColorIndex + 1) % bgColors.size
            findViewById<ConstraintLayout>(R.id.constraintLayout).setBackgroundColor(Color.parseColor(bgColors[currentColorIndex]))
        }

        findViewById<ImageButton>(R.id.imageButton).setOnClickListener {
            currentColorIndex = (currentColorIndex - 1 + bgColors.size) % bgColors.size
            findViewById<ConstraintLayout>(R.id.constraintLayout).setBackgroundColor(Color.parseColor(bgColors[currentColorIndex]))
        }


        val seekBar = findViewById<SeekBar>(R.id.seekBar3)
        val progressb = findViewById<ProgressBar>(R.id.progressBar5)

        progressb.max = seekBar.max
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                progressb.progress = progress
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
    }
}