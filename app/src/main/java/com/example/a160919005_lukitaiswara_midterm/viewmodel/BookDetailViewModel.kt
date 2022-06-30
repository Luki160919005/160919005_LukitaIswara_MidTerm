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
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.model.bookLists
import com.example.a160919005_lukitaiswara_midterm.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class BookDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    var bookLD = MutableLiveData<Book>()

    val TAG = "volleyTag"
    private var queue: RequestQueue?= null

    private val job = Job()
    val myBookLD = MutableLiveData<MyBooks>()

    fun fetchBook(ISBN:Int) {
        launch {
            val db = buildDB(getApplication())
            myBookLD.value =  db.booksDao().selectBooks(ISBN)
        }
    }

    fun changeLikeBook(ISBN:Int) {
        launch {
            val db = buildDB(getApplication())
            db.booksDao().updateBooksLike("1",ISBN)
        }
    }

    fun updateIsDone(uuid:Int){
        Log.d("checkString Delete", uuid.toString())
        launch {
            val db = buildDB(getApplication())
            db.booksDao().updateIsDone(uuid)
        }
    }

    fun addBook(list:List<MyBooks>) {
        launch {
            val db = buildDB(getApplication())
            db.booksDao().insertAll(*list.toTypedArray())
        }
    }


    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun changeLike(bISBN : String){
        queue = Volley.newRequestQueue(getApplication())

        var strGlobalVar = "["+bookLists.globalBookLike+"]"
        Log.d("showvoley url", strGlobalVar)

        val sType = object : TypeToken<List<Book>>() { }.type
        val result = Gson().fromJson<List<Book>>(strGlobalVar, sType)



        val findBookBasedID = result.find{(it.ISBN.equals(bISBN))}
        Log.d("showuser find", findBookBasedID.toString())
        if(findBookBasedID.toString() == "null"){

            queue = Volley.newRequestQueue(getApplication())
            var strGlobalVar = "http://192.168.100.215/myjson/book.json"



            val stringRequest = StringRequest(
                Request.Method.GET, strGlobalVar,
                { response ->
                    val sType = object : TypeToken<List<Book>>() { }.type
                    val result = Gson().fromJson<List<Book>>(response, sType)
                    val addBookLike = result.find{ it.ISBN == bISBN }

                    bookLists.globalBookLike +=
                        """
                        ,{
                            "ISBN":"$bISBN",
                            "title":"${(addBookLike?.title).toString()}",
                            "author":"${(addBookLike?.author).toString()}",
                            "publisher":"${(addBookLike?.publisher).toString()}",
                            "photo":"${(addBookLike?.photo).toString()}",
                            "rating": ${(addBookLike?.rating).toString()},
                            "description":"${(addBookLike?.description).toString()}",
                            "genres":"${(addBookLike?.genres).toString()}",
                            "date":"${(addBookLike?.date).toString()}",
                            "qty": ${(addBookLike?.qty).toString()},
                            "like": 0
                        }"""
                    Log.d("myBook url", bookLists.globalBookLike)



                },
                {
                    Log.d("showvoley2", it.toString())

                })

            stringRequest.tag = TAG
            queue?.add(stringRequest)

        }





    }
    fun update(title:String, author:String, publisher:String,photo:String,rating:String,
               description:String,date:String,qty:String,isbn:Int){
        launch {
            val db = buildDB(getApplication())
            db.booksDao().update(title,author,publisher,photo,rating,description, date, qty, isbn)
        }
    }

    fun fetch(pid : String) {


        queue = Volley.newRequestQueue(getApplication())
        var strGlobalVar = "http://192.168.100.215/myjson/book.json"



        val stringRequest = StringRequest(
            Request.Method.GET, strGlobalVar,
            { response ->
                val sType = object : TypeToken<List<Book>>() { }.type
                val result = Gson().fromJson<List<Book>>(response, sType)
                val findBookBasedID = result.find{ it.ISBN == pid }

                bookLD.value = findBookBasedID!!
                Log.d("showvoley2", bookLD.toString())
            },
            {
                Log.d("showvoley2", it.toString())

            })

        stringRequest.tag = TAG
        queue?.add(stringRequest)

        /*val student1 = Student(pid, pname, pbod, pphone,photo)
        studentLD.value = student1*/
    }

}
