package com.example.todoapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import com.example.todoapp.model.Todo
import com.example.todoapp.model.TodoDatabase
import com.example.todoapp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class DetailTodoViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    private val job = Job()
//    W9
    val todoLD = MutableLiveData<Todo>()
//    BATAS W9
    fun addTodo(todo:Todo) {
        launch {
            // WEEK 8
//            val db = Room.databaseBuilder(
//                getApplication(), TodoDatabase::class.java,
//                "newtododb").build()
            // BATAS WEEK 8

            // WEEK 9
            val db = buildDb(getApplication())
            // BATAS WEEK 9
            db.todoDao().insertAll(todo)
        }
    }

//    W9
    fun fetch(uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            todoLD.postValue(db.todoDao().selectTodo(uuid))
        }
    }

    fun update(title:String, notes:String, priority:Int, uuid:Int) {
        launch {
            val db = buildDb(getApplication())
            db.todoDao().update(title, notes, priority, uuid)
        }
    }

//    BATAS W9

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

}