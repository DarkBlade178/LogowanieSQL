package com.example.logowaniesql

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class LoginDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "notesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_FIRSTNAME = "fistName"
        private const val COLUMN_LASTNAME = "lastName"
        private const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        private const val COLUMN_CONFIRMPASSWORD = "confirmPassword"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_FIRSTNAME TEXT, $COLUMN_LASTNAME TEXT, $COLUMN_EMAIL EMAIL, $COLUMN_PASSWORD,$COLUMN_CONFIRMPASSWORD)"
        db?.execSQL(createTableQuery)
    }
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }
    fun insertNote(login: Login){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME, login.fistName)
            put(COLUMN_LASTNAME, login.lastName)
            put(COLUMN_LASTNAME, login.email)
            put(COLUMN_LASTNAME, login.password)
            put(COLUMN_LASTNAME, login.confirmPassword)

        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateNote(login: Login){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_FIRSTNAME, login.fistName)
            put(COLUMN_LASTNAME, login.lastName)
            put(COLUMN_EMAIL, login.email)
            put(COLUMN_PASSWORD, login.password)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(login.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getAllNotes(): List<Login> {
        val loginList = mutableListOf<Login>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME))
            val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME))
            val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
            val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
            val confirmPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONFIRMPASSWORD))

            val login = Login(id, firstName, lastName, email, password, confirmPassword)
            loginList.add(login)
        }
        cursor.close()
        db.close()
        return loginList
    }

    fun getNoteByID(loginId: Int) : Login {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $loginId"
        val cursor = db.rawQuery(query, null)
        cursor.moveToFirst()

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val firstName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_FIRSTNAME))
        val lastName = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_LASTNAME))
        val email = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_EMAIL))
        val password = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PASSWORD))
        val confirmPassword = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONFIRMPASSWORD))

        cursor.close()
        db.close()
        return Login(id, firstName, lastName, email, password, confirmPassword)
    }

    fun deleteNote(loginId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(loginId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}