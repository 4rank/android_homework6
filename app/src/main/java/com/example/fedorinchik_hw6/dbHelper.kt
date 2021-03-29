package com.example.fedorinchik_hw6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper

class dbHelper(
        context: Context?,
        name: String?,
        factory: CursorFactory?,
        version: Int) : SQLiteOpenHelper(context, name, factory, version) {

    val DATABASE_VERSION: Int = 1
    val DATABASE_NAME: String = "contactDB"
    val TABLE_CONTACTS: String = "contacts"

    val KEY_ID: String = "_id"
    val KEY_THEME: String = "theme"
    val KEY_MAIL: String = "mail"


    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("create table $TABLE_CONTACTS($KEY_ID integer primary key," +
                "$KEY_THEME text,$KEY_MAIL text)")

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table if exists " + TABLE_CONTACTS)
        onCreate(db)

    }
}