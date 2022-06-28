package com.example.a160919005_lukitaiswara_midterm.model

import androidx.room.*

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg myBooks: MyBooks)

    @Query("SELECT * FROM myBooks")
    suspend fun selectAllBooks(): List<MyBooks>

    @Query("SELECT * FROM myBooks WHERE ISBN= :isbn")
    suspend fun selectBooks(isbn:Int): MyBooks

    @Query("UPDATE myBooks SET is_done=1 WHERE ISBN=:id")
    suspend fun updateIsDone( id:Int)

    @Delete
    suspend fun deleteBooks(myBooks: MyBooks)
}