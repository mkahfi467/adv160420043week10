package com.example.todoapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.todoapp.R
import com.example.todoapp.viewmodel.DetailTodoViewModel
import com.google.android.material.textfield.TextInputEditText

class EditTodoFragment : Fragment() {
    private lateinit var viewModel: DetailTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailTodoViewModel::class.java)

        val txtJudulTodo = view.findViewById<TextView>(R.id.txtJudulTodo)
        txtJudulTodo.text = "Edit Todo"
        val btnAdd = view.findViewById<Button>(R.id.btnAdd)
        btnAdd.text = "Save Changes"

        val uuid = EditTodoFragmentArgs.fromBundle(requireArguments()).uuid
        viewModel.fetch(uuid)
        observeViewModel()

    }

    fun observeViewModel() {
        viewModel.todoLD.observe(viewLifecycleOwner, Observer {
            val txtTitle = view?.findViewById<TextInputEditText>(R.id.txtTitle)
            txtTitle?.setText(it.title)
            val txtNotes = view?.findViewById<TextInputEditText>(R.id.txtNotes)
            txtNotes?.setText(it.notes)
        })
    }

}