package com.example.nybaiboly

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.nybaiboly.databinding.ActivityHomeBinding
import com.google.android.material.switchmaterial.SwitchMaterial

class HomeActivity : AppCompatActivity() {


    private lateinit var binding: ActivityHomeBinding
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.selectedItemId = binding.bottomNavigationView.menu.getItem(1).itemId
        val (isModeSombre, isSetting, languageSelect, bibleFontSize) = DataSample.retrievePreferences(this)

        if (isModeSombre) {
            DataSample.savePreferences(this,true, isSetting,languageSelect,bibleFontSize)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        } else {
            DataSample.savePreferences(this,false, isSetting,languageSelect,bibleFontSize)
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }

        if (isSetting){
            DataSample.savePreferences(this,isModeSombre, false,languageSelect,bibleFontSize)
            replaceFragment(SettingFragment())
        }
        else{
            replaceFragment(HomeFragment())
        }
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.signet -> {
                    displayPreferences()
                    replaceFragment(LivreFragment())
                }
                R.id.menu -> replaceFragment(HomeFragment())
                R.id.setting -> replaceFragment(SettingFragment())
            }
            true
        }


    }
    private fun displayPreferences() {
        val (isSombre,isSetting, languageSelect, bibleFontSize) = DataSample.retrievePreferences(this)
        val message = "isModeSombre: $isSombre\nisSetting: $isSetting\nLanguage: $languageSelect\nBible Font Size: $bibleFontSize"
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_layout, fragment)
            .commit()
    }
}
