package com.example.a160919005_lukitaiswara_midterm.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
data class Book(
    val ISBN:String?,
    val title:String?,
    val author:String?,
    val publisher:String?,
    val photo:String?,
    val rating: String?,
    val description: String?,
    val genres:String?,
    val date:String?,
    val qty:String?,
    val like:String?
)


@Entity
data class MyBooks(
    @ColumnInfo(name="title")
    val title:String?,
    @ColumnInfo(name="author")
    val author:String?,
    @ColumnInfo(name="publisher")
    val publisher:String?,
    @ColumnInfo(name="photo")
    val photo:String?,
    @ColumnInfo(name="rating")
    val rating: String?,
    @ColumnInfo(name="description")
    val description: String?,
    @ColumnInfo(name="genres")
    val genres:String?,
    @ColumnInfo(name="date")
    val date:String?,
    @ColumnInfo(name="qty")
    val qty:String?,
    @ColumnInfo(name="like")
    val like:String?,
    @ColumnInfo(name="is_done")
    val is_done:Int,
    @ColumnInfo(name="priority")
    val priority:Int
) {
    @PrimaryKey(autoGenerate = true)
    var ISBN:Int =0
}

data class BooktDetail(
    val ISBN:String?,
    val title:String?,
    val author:String?,
    val publisher:String?,
    val photo:String?,
    val rating: String?,
    val description: String?,
    val genres:String?,
    val date:String?,
    val qty:String?,
    val like:String?

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