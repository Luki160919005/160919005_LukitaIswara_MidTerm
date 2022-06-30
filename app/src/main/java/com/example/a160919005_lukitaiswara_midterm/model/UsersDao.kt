package com.example.a160919005_lukitaiswara_midterm.model

import androidx.room.*

@Dao
interface UsersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg myUser: MyUser)

    @Query("SELECT * FROM myUser WHERE is_done=0 ORDER BY priority DESC")
    suspend fun selectAllUsers(): List<MyUser>

    @Query("SELECT * FROM myUser WHERE username= :id")
    suspend fun selectUser(id:String): MyUser

    @Query("SELECT * FROM myUser WHERE username= :name AND password= :password Limit 1")
    suspend fun checkUsernamePassword(name:String,password:String): List<MyUser>
}