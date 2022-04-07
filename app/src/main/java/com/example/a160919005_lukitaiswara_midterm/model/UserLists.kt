package com.example.a160919005_lukitaiswara_midterm.model

import android.app.Application
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UserList : Application() {
    companion object {
        var globalUsername = "";
        var globalImage = "";
        var globalUser = """
            {
                
                "username":"Alex",
                "password":"1",
                "profilePic":"https://images.unsplash.com/photo-1521572267360-ee0c2909d518?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80",
                "age":20,
                "email":"Alex@gmail.com",
                "address":"Yellow Street",
                "Gender":"Male"
                
            },
            {
                
                "username":"admin",
                "password":"123",
                "profilePic":"https://images.unsplash.com/photo-1644982647844-5ee1bdc5b114?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80",
                "age":18,
                "email":"admin@gmail.com",
                "address":"Green Wood Street",
                "Gender":"Male"
         
            },
            {
                
                "username":"yemy",
                "password":"asa",
                "profilePic":"https://images.unsplash.com/photo-1534528741775-53994a69daeb?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=464&q=80",
                "age":22,
                "email":"yemy@gmail.com",
                "address":"adaman Street",
                "Gender":"Female"
         
            }
        """

    }

    fun additionalUser(uName : String, uPassword:String){
        globalUser +=
            """
            ,{
                "username":"$uName",
                "password":"$uPassword"
            }"""
        Log.d("showuser global", globalUser)

    }

}