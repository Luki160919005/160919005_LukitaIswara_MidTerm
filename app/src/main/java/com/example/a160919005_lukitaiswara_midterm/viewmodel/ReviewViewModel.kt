package com.example.a160919005_lukitaiswara_midterm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.a160919005_lukitaiswara_midterm.model.*

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ReviewViewModel(application: Application): AndroidViewModel(application)  {

    val reviewLD = MutableLiveData<List<Review>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"

    private var queue: RequestQueue?= null

    fun refresh(isbn:String) {
        reviewLoadErrorLD.value = false
        loadingLD.value = true


        var strGlobalVar = "["+ ReviewList.globalReview+"]"


        val sType = object : TypeToken<List<Review>>() { }.type
        Log.d("showreview type", sType.toString())
        val result = Gson().fromJson<List<Review>>(strGlobalVar, sType)
        val findReviewBasedID = result.filter{it.whichISBN.equals(isbn) }
        Log.d("showreview type", findReviewBasedID.toString())

        reviewLD.value = findReviewBasedID
        loadingLD.value = false


    }
    fun addReview(isbn:String, uReview:String) {

        queue = Volley.newRequestQueue(getApplication())


        ReviewList.globalReview +=
            """,{
                "myUser":"${UserList.globalUsername}",
                "whichISBN":"$isbn",
                "reviewInfo":"$uReview",
                 "profImage":"${UserList.globalImage}"
               
                
                
            }"""


        Log.d("showreview global", ReviewList.globalReview)






    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}