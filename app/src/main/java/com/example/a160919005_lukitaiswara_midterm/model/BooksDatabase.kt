package com.example.a160919005_lukitaiswara_midterm.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.a160919005_lukitaiswara_midterm.util.MIGRATION_1_2
import com.example.a160919005_lukitaiswara_midterm.util.MIGRATION_2_3

@Database(entities = arrayOf(MyUser::class, MyBooks::class, MyReview::class), version =  4)
abstract class BooksDatabase: RoomDatabase() {
    abstract fun booksDao(): BooksDao
    abstract fun usersDao(): UsersDao
    abstract fun reviewsDao(): ReviewsDao

    companion object {
        @Volatile private var instance: BooksDatabase ?= null
        private val LOCK = Any()

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BooksDatabase::class.java,
            "booksdb2")
            .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
            .build()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        fun getInstance(context: Context): BooksDatabase {
            synchronized(this) {
                return instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    BooksDatabase::class.java,
                    "school_db"
                ).build().also {
                    instance = it
                }
            }
        }



    }

}