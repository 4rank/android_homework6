package com.example.fedorinchik_hw6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.fedorinchik_hw6.TodoTaskContract

class TodoDbHelper(context: Context) : SQLiteOpenHelper(context, TodoTaskContract.DB_NAME, null, TodoTaskContract.DB_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE " + TodoTaskContract.TaskEntry.TASKS + " ( " +
                TodoTaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TodoTaskContract.TaskEntry.TASK_TITLE + " TEXT NOT NULL);"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TodoTaskContract.TaskEntry.TASKS)
        onCreate(db)
    }
}