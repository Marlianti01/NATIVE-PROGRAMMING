package com.example.roomdatabasedemo

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDAO {

    @Insert
    suspend fun insertTask(task: Task)

    @Query("SELECT * FROM task_table WHERE userId = :userId")
    suspend fun getTasksByUserId(userId: Int): List<Task>

    @Delete
    suspend fun delete(task: Task)

    @Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task>>
}
