package com.example.fedorinchik_hw6

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {
    private val TAG_LOG = "MainActivity"
    private lateinit var todoHelper: TodoDbHelper
    private lateinit var todoListView: ListView
    private var todoAdapter: ArrayAdapter<String>? = null
    private lateinit var taskMain: TextView
    private lateinit var theme: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoListView = findViewById(R.id.list_todo)
        theme = findViewById(R.id.iv_theme)
        todoHelper = TodoDbHelper(this)
        updateUI()
        theme.setOnClickListener {
            setTheme()
        }
    }

    private fun updateUI() {
        val taskList = mutableListOf<String>()
        val db = todoHelper.readableDatabase
        val cursor = db.query(TodoTaskContract.TaskEntry.TASKS,
                arrayOf(TodoTaskContract.TaskEntry._ID, TodoTaskContract.TaskEntry.TASK_TITLE), null, null, null, null, null)
        while (cursor.moveToNext()) {
            val idx = cursor.getColumnIndex(TodoTaskContract.TaskEntry.TASK_TITLE)
            taskList.add(cursor.getString(idx))
        }
        if (todoAdapter == null) {
            todoAdapter = ArrayAdapter(this,
                    R.layout.todo_layout,
                    R.id.task_title_main,
                    taskList)
            todoListView.adapter = todoAdapter
        } else {
            todoAdapter?.clear()
            todoAdapter?.addAll(taskList)
            todoAdapter?.notifyDataSetChanged()
        }
        cursor.close()
        db.close()
    }

    fun deleteTask(view: View) {
        val parent = view.getParent() as View
        taskMain = parent.findViewById(R.id.task_title_main)
        val titleMain = taskMain.text.toString()
        val db = todoHelper.writableDatabase
        db.delete(TodoTaskContract.TaskEntry.TASKS,
                TodoTaskContract.TaskEntry.TASK_TITLE + " = ?",
                arrayOf(titleMain))
        db.close()
        updateUI()
    }

    fun startView(view: View) {
        val parent = view.parent as View
        taskMain = parent.findViewById(R.id.task_title_main)
        val intent = Intent(this, TodoInfo::class.java)
        intent.putExtra("TASK_TITLE", taskMain.text)
        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_add_task -> {
                val taskEditText = EditText(this)
                val dialog = AlertDialog.Builder(this)
                        .setTitle("Задача")
                        .setView(taskEditText)
                        .setPositiveButton("Добавить") { dialog, which ->
                            val task = taskEditText.text.toString()
                            val timeTask = getTime() + "\n" + task
                            val db = todoHelper.writableDatabase
                            val values = ContentValues()
                            values.put(TodoTaskContract.TaskEntry.TASK_TITLE, timeTask)
                            db.insertWithOnConflict(TodoTaskContract.TaskEntry.TASKS,
                                    null,
                                    values,
                                    SQLiteDatabase.CONFLICT_REPLACE)
                            db.close()

                            Log.d(TAG_LOG, "Добавить: $timeTask")
                            updateUI()
                        }
                        .setNegativeButton("Отмена", null)
                        .create()
                dialog.show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getTime(): String {
        val dateFormat: DateFormat = SimpleDateFormat("dd.MM.yyyy")
        val date = Date()
        return dateFormat.format(date)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    private fun setTheme() {
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        recreate()
    }
}