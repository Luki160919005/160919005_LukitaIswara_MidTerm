package com.example.a160919005_lukitaiswara_midterm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.a160919005_lukitaiswara_midterm.model.*
import com.example.a160919005_lukitaiswara_midterm.util.buildDB
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*


import java.security.KeyStore
import kotlin.coroutines.CoroutineContext

class MyUserViewModel(application: Application)
    : AndroidViewModel(application), CoroutineScope  {

    val myUserLD = MutableLiveData<List<MyUser>>()
    val userLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    val validLogin = MutableLiveData<Number>()
    var returnReadAllData = MutableLiveData<List<MyUser>?>()
    val TAG = "volleyTag"
    private var job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun namePasswordVerificatioN(usernameCheck:String, passwordCheck:String):List<MyUser>?{
        var valueCheck = true;
        launch {
            Log.d("showuser checkString", usernameCheck);
            Log.d("showuser checkString", passwordCheck);
            val db = buildDB(getApplication())
            Log.d("showuser checkString", db.toString());

            val readAllData: List<MyUser> = db.usersDao().checkUsernamePassword(usernameCheck,passwordCheck)
            Log.d("showuser checkString", readAllData.toString());

            var findUser = readAllData.find {
                (it.username.equals(usernameCheck)) && (it.password.equals(passwordCheck))
            }

            delay(1000L)
            Log.d("showuser checkString find", findUser.toString())
            if(findUser.toString() != "null"){
                UserList.globalUsername = usernameCheck.toString();
                valueCheck = true
                validLogin.value = 1    // berhasil
                returnReadAllData.value = listOf(db.usersDao().selectUser(usernameCheck))
                Log.d("showuser get return = ", "panjang size = " + returnReadAllData.value!!.size)
//                true
            }
            else{
                valueCheck = false
                validLogin.value = 2    // gagal
//                false
            }
            Log.d("showuser checkString value", valueCheck.toString())
        }
        Log.d("checkString value", valueCheck.toString())
        return returnReadAllData.value

    }
    private var queue: RequestQueue?= null

    fun searchUserInfo(uName : String): List<MyUser> {

        var usersData = MyUser("","password","picture","age",
            "email","address","checkGender",0,0)

        var readAllData: List<MyUser> = listOf(usersData)
        launch {
            Log.d("checkString", uName);

            val db = buildDB(getApplication())
            Log.d("checkString read", db.toString());

            delay(1000L)
            readAllData = listOf(db.usersDao().selectUser(uName))
            Log.d("checkString readAll1", readAllData.toString());



        }
        Log.d("checkString readAll2", readAllData.toString());
        return readAllData



    }



    fun addUser(list:List<MyUser>) {
        launch {
            val db = buildDB(getApplication())
            db.usersDao().insertAll(*list.toTypedArray())
        }
    }
}