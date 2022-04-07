package com.example.a160919005_lukitaiswara_midterm.model

data class Book(
    var ISBN:String?,
    var title:String?,
    var author:String?,
    var publisher:String?,
    var photo:String?,
    var rating: String?,
    var description: String?,
    var genres:String?,
    var date:String?,
    var qty:String?,
    var like:String?
)
data class User(

    var username:String?,
    var password:String?,
    var profilePic:String?,
    var age:String?,
    var email:String?,
    var address:String?,
    var Gender:String?

)



data class Review(

    var myUser:String?,
    var whichISBN:String?,
    var reviewInfo:String?,
    var profImage:String?

)

public class myNewUser(var name: String, private var password: String, var profilePic: String, var userEmail: String) {


    @JvmName("setName1")
    fun setName(name: String) {
        this.name = name
    }
    fun setPassword(password: String) {
        this.password = password
    }
    fun setPic(profilePic: String) {
        this.profilePic = profilePic
    }

    fun setEmail(userEmail: String) {
        this.userEmail = userEmail
    }
    @JvmName("getName1")
    fun getName(): String {
        return name
    }
    @JvmName("getPassword")
    fun getPassword(): String {
        return password
    }
    @JvmName("getPic1")
    fun getPic(): String {
        return profilePic
    }
    @JvmName("getPicture")
    fun getEmail(): String {
        return userEmail
    }

}