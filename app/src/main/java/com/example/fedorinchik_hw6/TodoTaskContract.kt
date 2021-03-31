package com.example.fedorinchik_hw6

import android.provider.BaseColumns

class TodoTaskContract {
    companion object {
        val DB_NAME = "com.fofism.todo.db"
        val DB_VERSION = 1
    }

    class TaskEntry : BaseColumns {

        companion object {
            val TASKS = "tasks"
            val TASK_TITLE = "main"
            val _ID = BaseColumns._ID
        }
    }
}