package com.example.a160919005_lukitaiswara_midterm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.a160919005_lukitaiswara_midterm.model.Book
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.bookLists
import com.example.a160919005_lukitaiswara_midterm.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class MyBookViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope {

    val myBookLD = MutableLiveData<List<MyBooks>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var job = Job()
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun refresh() {
        loadingLD.value = true
        bookLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            myBookLD.value = db.booksDao().selectAllBooks()
        }
    }

    fun addBook(list:List<MyBooks>) {
        launch {
            val db = buildDB(getApplication())
            db.booksDao().insertAll(*list.toTypedArray())
        }
    }

    fun refreshLike() {
        loadingLD.value = true
        bookLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            myBookLD.value = db.booksDao().selecsBooksLike()
        }
    }

    fun clearTask(myBooks: MyBooks) {
        launch {
            val db = buildDB(getApplication())
            db.booksDao().updateIsDone(myBooks.ISBN)
            myBookLD.value = db.booksDao().selectAllBooks()
        }
    }
}