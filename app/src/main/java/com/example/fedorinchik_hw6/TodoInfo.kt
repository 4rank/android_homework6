package com.example.fedorinchik_hw6

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TodoInfo : AppCompatActivity() {

    private var mainTodo: String? = null
    private var elseTodo: String? = null
    private var dataTodo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_final)
        val mainText = findViewById<TextView>(R.id.todo_final_main)
        val elseText = findViewById<TextView>(R.id.todo_final_else)
        val dataText = findViewById<TextView>(R.id.todo_final_data)
        val exitButton = findViewById<Button>(R.id.button_todo_final)

        val bundle = intent.extras
        mainTodo = bundle?.getString(TODO_VALUES)
        elseTodo = bundle?.getString(TODO_ELSE)
        dataTodo = bundle?.getString(TODO_DATA)
        mainTodo?.let {
            mainText.text = it
        }
        elseTodo?.let {
            elseText.text = it
        }
        dataTodo?.let {
            dataText.text = it
        }
        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}