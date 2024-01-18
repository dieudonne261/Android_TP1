package com.example.exammisem

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context?) : SQLiteOpenHelper(context, Constants.DB_NAME, null, Constants.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Constants.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME)
        onCreate(db)
    }

    fun insertInfo(nom: String, genre: String, age: String, addTimeStamp: String, updateTimeStamp: String): Long {
        val db = writableDatabase
        val values = ContentValues()
        values.put(Constants.C_NOM, nom)
        values.put(Constants.C_GENRE, genre)
        values.put(Constants.C_AGE, age)
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp)
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp)

        val id = db.insert(Constants.TABLE_NAME, null, values)
        db.close()
        return id
    }

    fun updateInfo(id: String, nom: String, genre: String, age: String, addTimeStamp: String, updateTimeStamp: String) {
        val db = writableDatabase
        val values = ContentValues()
        values.put(Constants.C_NOM, nom)
        values.put(Constants.C_GENRE, genre)
        values.put(Constants.C_AGE, age)
        values.put(Constants.C_ADD_TIMESTAMP, addTimeStamp)
        values.put(Constants.C_UPDATE_TIMESTAMP, updateTimeStamp)

        db.update(Constants.TABLE_NAME, values, Constants.C_ID + " = ?", arrayOf(id))
        db.close()
    }

    fun deleteInfo(id: String) {
        try {
            val db = writableDatabase
            db.delete(Constants.TABLE_NAME, Constants.C_ID + " = ?", arrayOf(id))
            db.close()
        } catch (e: Exception) {
            println(e)
        }
    }

    fun getAllData(): ArrayList<Model> {
        val arrayList = ArrayList<Model>()

        val selectQuery = "SELECT * FROM " + Constants.TABLE_NAME
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery(selectQuery, null)
        if (cursor.moveToNext()) {
            do {
                val model = Model(
                    "" + cursor.getInt(cursor.getColumnIndex(Constants.C_ID)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.C_NOM)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.C_GENRE)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.C_AGE)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.C_ADD_TIMESTAMP)),
                    "" + cursor.getString(cursor.getColumnIndex(Constants.C_UPDATE_TIMESTAMP))
                )
                arrayList.add(model)
            } while (cursor.moveToNext())
        }
        db.close()
        return arrayList
    }
}
