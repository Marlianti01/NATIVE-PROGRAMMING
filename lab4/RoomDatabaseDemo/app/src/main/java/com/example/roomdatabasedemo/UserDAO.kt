package com.example.roomdatabasedemo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDAO {
    @Insert
    suspend fun insert(user: User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
     fun getAllUsers(): LiveData<List<User>>

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("SELECT * FROM user_table WHERE id = :userId LIMIT 1")
     fun getUserById(userId: Int): LiveData<User?>

    @Query("SELECT * FROM user_table WHERE name LIKE :name")
    suspend fun  searchUserByName(name: String): List<User>


}