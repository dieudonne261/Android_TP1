package com.example.exammisem

object Constants {
    const val DB_NAME = "Person_Info"
    const val DB_VERSION = 1
    const val TABLE_NAME = "Person_Info_Table"
    const val C_ID = "ID"
    const val C_NOM = "NOM"
    const val C_GENRE = "GENRE"
    const val C_AGE = "AGE"
    const val C_ADD_TIMESTAMP = "ADD_TIMESTAMP"
    const val C_UPDATE_TIMESTAMP = "UPDATE_TIMESTAMP"
    const val CREATE_TABLE = "CREATE TABLE $TABLE_NAME (" +
            "$C_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
            "$C_NOM TEXT," +
            "$C_GENRE TEXT," +
            "$C_AGE TEXT," +
            "$C_ADD_TIMESTAMP TEXT," +
            "$C_UPDATE_TIMESTAMP TEXT);"
}
