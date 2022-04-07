package com.example.a160919005_lukitaiswara_midterm.model

import android.app.Application

class ReviewList : Application() {
    companion object {

        var globalReview = """
            {
                
                "myUser":"Alex",
                "whichISBN":"1",
             
                "reviewInfo":"Good Book",
                "profImage":"https://images.unsplash.com/photo-1521572267360-ee0c2909d518?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80"
               
         
            },
            {
                
                "myUser":"admin",
                "whichISBN":"2",
             
                "reviewInfo":"Bad Book",
                "profImage":"https://d2qp0siotla746.cloudfront.net/img/use-cases/profile-picture/template_3.jpg"
               
         
            },
            {
                
                "myUser":"yemy",
                "whichISBN":"3",
             
                "reviewInfo":"Great Book",
                "profImage":"https://images.unsplash.com/photo-1644982647844-5ee1bdc5b114?ixlib=rb-1.2.1&ixid=MnwxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=774&q=80"
               
         
            },{
                    "myUser":"admin",
                    "whichISBN":"2",
                    "reviewInfo":"Hello There",
                    "profImage":"https://images.unsplash.com/photo-1521572267360-ee0c2909d518?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MjJ8fHByb2ZpbGV8ZW58MHx8MHx8&w=1000&q=80"
               
                    
                }
        """
    }


}