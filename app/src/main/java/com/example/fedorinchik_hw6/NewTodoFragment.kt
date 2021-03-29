package com.example.fedorinchik_hw6

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import android.widget.EditText
import androidx.fragment.app.Fragment


private const val TODO_PARAM1 = "param1"
private const val TODO_PARAM2 = "param2"

class NewTodoFragment : Fragment() {

    private var todoFrag1: String? = null
    private var todoFrag2: String? = null

    var dataNow: String? = null
    var mainTodo: String? = null
    var elseTodo: String? = null

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
        return inflater.inflate(R.layout.new_todo_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        val mainText = view?.findViewById<EditText>(R.id.et_todo)
        val elseText = view?.findViewById<EditText>(R.id.et_todo_2)
        val data = view?.findViewById<CalendarView>(R.id.calendarView)
        val exitButton = view?.findViewById<Button>(R.id.button_new_todo)
        data?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            "$dayOfMonth.${month + 1}.$year"
        }
        mainText?.setOnClickListener {
            if (TextUtils.isEmpty(mainText.text.toString())) {
                return@setOnClickListener
            }
            mainText.text.toString()
        }
        elseText?.setOnClickListener {
            if (TextUtils.isEmpty(mainText?.text.toString())) {
                return@setOnClickListener
            }
            mainText?.text.toString()
        }
        exitButton?.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("MainTodo", mainTodo)
            bundle.putString("ElseTodo", elseTodo)
            bundle.putString("DataTodo", dataNow)
            val bun = TodoListFragment()
            bun.arguments = bundle
        }
    }
}