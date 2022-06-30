package com.example.a160919005_lukitaiswara_midterm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.example.a160919005_lukitaiswara_midterm.model.MyBooks
import com.example.a160919005_lukitaiswara_midterm.model.MyReview
import com.example.a160919005_lukitaiswara_midterm.model.MyUser
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.example.a160919005_lukitaiswara_midterm.util.buildDB
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MyReviewViewModel (application: Application)
    : AndroidViewModel(application), CoroutineScope {


    val myReviewLD = MutableLiveData<List<MyReview>>()
    val reviewLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private var queue: RequestQueue?= null

    fun refreshReviews(ISBN:Int) {
        loadingLD.value = true
        reviewLoadErrorLD.value = false
        launch {
            val db = buildDB(getApplication())
            myReviewLD.value = db.reviewsDao().selecReview(ISBN)
        }
    }

    fun updateIsDone(uuid:Int){
        Log.d("checkString Delete", uuid.toString())
        launch {
            val db = buildDB(getApplication())
            db.reviewsDao().updateIsDone(uuid)
        }
    }


    fun addReview(list:List<MyReview>) {
        launch {
            val db = buildDB(getApplication())
            db.reviewsDao().insertAll(*list.toTypedArray())
        }
    }
    fun clearTask(myReview: MyReview) {
        launch {
            val db = buildDB(getApplication())
            db.booksDao().updateIsDone(myReview.idReview)
            myReviewLD.value = db.reviewsDao().selectAllReviews()
        }
    }
}
