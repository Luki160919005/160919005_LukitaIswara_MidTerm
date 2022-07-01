package com.example.a160919005_lukitaiswara_midterm.model

import androidx.room.*

@Dao
interface BooksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg myBooks: MyBooks)

    @Query("SELECT * FROM myBooks WHERE is_done=0 ORDER BY priority DESC")
    suspend fun selectAllBooks(): List<MyBooks>

    @Query("SELECT * FROM myBooks WHERE is_done=0 AND likeBook='1' ORDER BY priority DESC")
    suspend fun selecsBooksLike():  List<MyBooks>

    @Query("SELECT * FROM myBooks WHERE ISBN= :id")
    suspend fun selectBooks(id:Int): MyBooks

    @Query("UPDATE myBooks SET likeBook=:bookLike WHERE ISBN=:isbn")
    suspend fun updateBooksLike(bookLike:String,isbn:Int)

    @Query("UPDATE myBooks SET title=:title, author= :author,publisher= :publisher, photo= :photo,rating= :rating," +
            "description= :description,date= :date,qty= :qty, priority= :prior WHERE ISBN=:isbn")
    suspend fun update(title:String, author:String, publisher:String,photo:String,rating:String,
                       description:String,date:String,qty:String,isbn:Int, prior:Int)


    @Query("UPDATE myBooks SET is_done=1 WHERE ISBN=:id")
    suspend fun updateIsDone( id:Int)

    @Delete
    suspend fun deleteBooks(myBooks: MyBooks)
}