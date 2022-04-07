package com.example.a160919005_lukitaiswara_midterm.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley
import com.example.a160919005_lukitaiswara_midterm.model.User
import com.example.a160919005_lukitaiswara_midterm.model.UserList
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserViewModel (application: Application): AndroidViewModel(application) {
    val TAG = "volleyTag"
    val userLD = MutableLiveData<List<User>>()

    private var queue: RequestQueue?= null

    fun searchUser(uName : String, uPassword:String):Boolean {

        queue = Volley.newRequestQueue(getApplication())


        var mUser = UserList()
        var strGlobal = "["+UserList.globalUser+"]"
        Log.d("showuser url", strGlobal)
        Log.d("showuser name", uName)
        Log.d("showuser pass", uPassword)
        val sType = object : TypeToken<List<User>>() { }.type

        Log.d("showuser type", sType.toString())
        Log.d("showuser type", uPassword)

        val result = Gson().fromJson<List<User>>(strGlobal, sType)


        Log.d("showuser res", result.toString())

        val findUser = result.find{(it.username.equals(uName))&&(it.password.equals(uPassword))}
        Log.d("showuser find", findUser.toString())
        if(findUser.toString() != "null"){
            UserList.globalUsername = uName.toString();

            return true
        }
        else{
            return false
        }


    }

    fun searchUserInfo(uName : String): List<User> {

        queue = Volley.newRequestQueue(getApplication())



        var strGlobal = "["+UserList.globalUser+"]"
        Log.d("showuser url", strGlobal)
        Log.d("showuser name", uName)

        val sType = object : TypeToken<List<User>>() { }.type

        Log.d("showuser type", sType.toString())


        val result = Gson().fromJson<List<User>>(strGlobal, sType)


        Log.d("showuser res", result.toString())

        val findUser = result.filter{(it.username.equals(uName))}



        return findUser




    }

    fun addUser(uName : String, uPassword:String, uPicture:String, uAge:String, uEmail:String
                , uAddress:String, uGender:String) {

        queue = Volley.newRequestQueue(getApplication())


        var mUser = UserList()


        Log.d("showuser name", uName)
        Log.d("showuser pass", uPassword)

        //UserList.additionalUser(uName,uPassword)

        UserList.globalUser +=
            """
            ,{
                "username":"$uName",
                "password":"$uPassword",
                "profilePic":"$uPicture",
                "age":$uAge,
                "email":"$uEmail",
                "address":"$uAddress",
                "Gender":"$uGender"
            }"""

        Log.d("showuser global", UserList.globalUser)






    }
}