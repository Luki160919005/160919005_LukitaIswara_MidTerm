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

import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.model.bookLists
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.FileReader

class BookViewModel(application: Application): AndroidViewModel(application) {
    val booksLD = MutableLiveData<List<Book>>()
    val bookLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"

    private var queue: RequestQueue?= null

    fun refreshGenre(bookGenre:String, searchSentence:String){

        bookLoadErrorLD.value = false
        loadingLD.value = true
        Log.d("showvoley", "masuk");


        queue = Volley.newRequestQueue(getApplication())
        var strGlobalVar = "http://192.168.100.99/myjson/book.json"

        val stringRequest = StringRequest(
            Request.Method.GET, strGlobalVar,
            { response ->

                val sType = object : TypeToken<List<Book>>() { }.type
                val result = Gson().fromJson<List<Book>>(response, sType)
                val filterGenreBook = result.filter{(it.genres.equals(bookGenre.toString()))&&(it.title!!.contains(searchSentence, ignoreCase = true)) }
                Log.d("showvoley Book1", result.toString())
                Log.d("showvoley Book2", filterGenreBook.toString())

                booksLD.value = filterGenreBook
                loadingLD.value = false


            },
            {
                Log.d("showvoley", it.toString())
                bookLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)



    }
    fun refreshLike(){

        bookLoadErrorLD.value = false
        loadingLD.value = true
        Log.d("showvoley", "masuk");

        queue = Volley.newRequestQueue(getApplication())
        var strGlobalVar = "["+bookLists.globalBookLike+"]"
        val sType = object : TypeToken<List<Book>>() { }.type
        val result = Gson().fromJson<List<Book>>(strGlobalVar, sType)
        booksLD.value = result
        loadingLD.value = false



    }

    fun refresh() {
        bookLoadErrorLD.value = false
        loadingLD.value = true
        Log.d("showvoley", "masuk");

        queue = Volley.newRequestQueue(getApplication())
        var strGlobalVar = "http://192.168.100.99/myjson/book.json"

        val stringRequest = StringRequest(
            Request.Method.GET, strGlobalVar,
            { response ->

                val sType = object : TypeToken<List<Book>>() { }.type
                val result = Gson().fromJson<List<Book>>(response, sType)
                booksLD.value = result
                loadingLD.value = false
                Log.d("showvoley", response.toString())
            },
            {
                Log.d("showvoley", it.toString())
                bookLoadErrorLD.value = false
                loadingLD.value = false
            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }


}