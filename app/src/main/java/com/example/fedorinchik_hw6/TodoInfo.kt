package com.example.fedorinchik_hw6

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TodoInfo : AppCompatActivity() {

    private var mainTodo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.todo_final)
        val mainText = findViewById<TextView>(R.id.todo_final_main)
        val exitButton = findViewById<Button>(R.id.button_todo_final)

        val bundle = intent.extras
        mainTodo = bundle?.getString("TASK_TITLE")
        mainTodo?.let {
            mainText.text = it
        }

        exitButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}