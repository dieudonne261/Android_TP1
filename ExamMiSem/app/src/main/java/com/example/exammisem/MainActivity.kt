package com.example.exammisem


import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var newAdd: FloatingActionButton
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mRecyclerView = findViewById(R.id.recyclerView)
        databaseHelper = DatabaseHelper(this)

        showRecord()

        newAdd = findViewById(R.id.nouveaubtn)
        newAdd.setOnClickListener {
            val intent = Intent(this@MainActivity, NewActivity::class.java)
            intent.putExtra("editMode", false)
            startActivity(intent)
        }
    }

    private fun showRecord() {
        val adapter = Adapter(this@MainActivity, databaseHelper.getAllData())
        mRecyclerView.adapter = adapter
    }

    public override fun onResume() {
        super.onResume()
        showRecord()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true)
        }
        return super.onKeyDown(keyCode, event)
    }
}
