package com.example.a160919005_lukitaiswara_midterm.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface ReviewsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg myReview: MyReview)

    @Query("SELECT * FROM myReview WHERE is_done=0 ORDER BY priority DESC")
    suspend fun selectAllReviews(): List<MyReview>

    @Query("SELECT * FROM myReview WHERE whichISBN= :whichISBN")
    suspend fun selecReview(whichISBN:Int): List<MyReview>


}