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

class BookDetailViewModel(application: Application): AndroidViewModel(application) {
    var bookLD = MutableLiveData<Book>()
    /*
    fun fetch(pid : String, pname : String, pbod : String, pphone : String, photo:String) {
        val student1 = Student(pid, pname, pbod, pphone,photo)
        studentLD.value = student1
    }*/
    val TAG = "volleyTag"
    private var queue: RequestQueue?= null

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
            var strGlobalVar = "http://192.168.100.99/myjson/book.json"



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

    fun fetch(pid : String) {


        queue = Volley.newRequestQueue(getApplication())
        var strGlobalVar = "http://192.168.100.99/myjson/book.json"



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
