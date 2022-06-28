package com.example.a160919005_lukitaiswara_midterm.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.a160919005_lukitaiswara_midterm.R
import com.example.a160919005_lukitaiswara_midterm.model.BooksDatabase
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


val DB_NAME = "booksdb"

fun buildDB(context:Context): BooksDatabase {
    val db = Room.databaseBuilder(context, BooksDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
        .build()

    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE myBooks ADD COLUMN priority INTEGER DEFAULT 3 not null")
    }
}

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "ALTER TABLE myBooks ADD COLUMN priority INTEGER DEFAULT 0 not null")
    }
}



fun ImageView.loadImage(url:String, progressBar:ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this,  object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })


}

@BindingAdapter("android:imageurl","android:progressBar")
fun loadPhotoUrl(v: ImageView, url:String, pb:ProgressBar){
    v.loadImage(url, pb)

}

fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "${context.packageName}-$name"
            val channel = NotificationChannel(channelId, name, importance)
            channel.description = description
            channel.setShowBadge(showBadge)

            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }


    }
}



fun ImageView.loadImage2(url:String){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.ic_baseline_error_24)
        .into(this,  object: Callback {
            override fun onSuccess() {

            }
            override fun onError(e: Exception?) {
            }
        })


}

