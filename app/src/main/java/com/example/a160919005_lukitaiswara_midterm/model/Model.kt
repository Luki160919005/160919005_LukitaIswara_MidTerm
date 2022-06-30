package com.example.a160919005_lukitaiswara_midterm.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MyBooks(
    @ColumnInfo(name="title")
    var title:String?,
    @ColumnInfo(name="author")
    var author:String?,
    @ColumnInfo(name="publisher")
    var publisher:String?,
    @ColumnInfo(name="photo")
    var photo:String?,
    @ColumnInfo(name="rating")
    var rating: String?,
    @ColumnInfo(name="description")
    var description: String?,
    @ColumnInfo(name="genres")
    var genres:String?,
    @ColumnInfo(name="date")
    var date:String?,
    @ColumnInfo(name="qty")
    var qty:String?,
    @ColumnInfo(name="likeBook")
    var likeBook:String?,
    @ColumnInfo(name="is_done")
    var is_done:Int,
    @ColumnInfo(name="priority")
    var priority:Int
) {
    @PrimaryKey(autoGenerate = true)
    var ISBN:Int =0
}

@Entity
data class MyReview(
    @ColumnInfo(name="myUser")
    var myUser:String?,
    @ColumnInfo(name="whichISBN")
    var whichISBN:Int?,
    @ColumnInfo(name="reviewInfo")
    var reviewInfo:String?,
    @ColumnInfo(name="profImage")
    var profImage:String?,
    @ColumnInfo(name="is_done")
    var is_done:Int,
    @ColumnInfo(name="priority")
    var priority:Int
) {
    @PrimaryKey(autoGenerate = true)
    var idReview:Int =0
}

@Entity
data class MyUser(
    @ColumnInfo(name="username")
    var username:String?,
    @ColumnInfo(name="password")
    var password:String?,
    @ColumnInfo(name="profilePic")
    var profilePic:String?,
    @ColumnInfo(name="age")
    var age:String?,
    @ColumnInfo(name="email")
    var email:String?,
    @ColumnInfo(name="address")
    var address:String?,
    @ColumnInfo(name="Gender")
    var Gender:String?,
    @ColumnInfo(name="is_done")
    var is_done:Int,
    @ColumnInfo(name="priority")
    var priority:Int
) {
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}

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