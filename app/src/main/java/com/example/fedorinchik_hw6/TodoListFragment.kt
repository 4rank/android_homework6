package com.example.fedorinchik_hw6

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


const val TODO_VALUES = "todo_name"
const val TODO_ELSE = "todo_name_else"
const val TODO_DATA = "todo_data"

private const val TODO_PARAM1 = "param1"
private const val TODO_PARAM2 = "param2"

class TodoListFragment : Fragment() {

    private val todos = mutableListOf(
            TodoData("Основная задача", "Подзадачи", "27.03.21"),
            TodoData("Основная задача1", "Подзадачи1", "28.03.21"),
            TodoData("Основная задача2", "Подзадачи2", "29.03.21"),
            TodoData("Основная задача3", "Подзадачи3", "30.03.21")
    )

    private lateinit var recycler: RecyclerView
    private var todoFrag1: String? = null
    private var todoFrag2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            todoFrag1 = it.getString(TODO_PARAM1)
            todoFrag2 = it.getString(TODO_PARAM2)
        }

    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val startFragment = inflater.inflate(R.layout.todo_list_fragment, container, false)
        recyclerStart(startFragment)
        return startFragment
    }

    override fun onStart() {
        super.onStart()
        val buttonDel = view?.findViewById<Button>(R.id.button_todo_layout)
        buttonDel?.setOnClickListener {
            deleteTodo(1)
        }
    }

    private fun recyclerStart(view: View) {
        recycler = view.findViewById(R.id.RecyclerView_TODO)
        val adapter = TodoAdapter(todos) {
            val intent = Intent(this.context, TodoInfo::class.java)
            intent.putExtra(TODO_VALUES, it.daily)
            intent.putExtra(TODO_ELSE, it.dailyElse)
            intent.putExtra(TODO_DATA, it.time)
            startActivity(intent)
        }
        recycler.adapter = adapter
        recycler.adapter = adapter
        recycler.layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }

    fun deleteTodo(removeIndex:Int) {
        todos.removeAt(removeIndex)
    }
}