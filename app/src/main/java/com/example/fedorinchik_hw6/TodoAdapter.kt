package com.example.fedorinchik_hw6

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TodoAdapter(
        private val todos: List<TodoData>, private val onClick: (TodoData) -> Unit
) :
        RecyclerView.Adapter<TodoAdapter.ViewHolderCurrentTodo>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCurrentTodo {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_layout, parent, false)
        return ViewHolderCurrentTodo(view, onClick)
    }

    override fun getItemCount(): Int {
        return todos.size
    }

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }

    override fun onBindViewHolder(holder: ViewHolderCurrentTodo, pos: Int) {
        holder.bindViewHolder(todos[pos])
    }

    class ViewHolderCurrentTodo(view: View, val onClick: (TodoData) -> Unit) :
            RecyclerView.ViewHolder(view) {
        private var currentViewHolderTodo: TodoData? = null
        private val textMain = view.findViewById<TextView>(R.id.text_todo_main)
        private val textElse = view.findViewById<TextView>(R.id.text_todo_else)
        private val textData= view.findViewById<TextView>(R.id.text_todo_data)
        init {
            view.setOnClickListener {
                currentViewHolderTodo?.let {
                    onClick(it)
                }
            }
        }

        fun bindViewHolder(currentTodo: TodoData) {
            currentViewHolderTodo = currentTodo
            textMain.text = currentTodo.daily
            textElse.text = currentTodo.dailyElse
            textData.text = currentTodo.time
        }
    }
}