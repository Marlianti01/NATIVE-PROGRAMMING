package com.example.roomdatabasedemo

import androidx.lifecycle.LiveData


class UserRepository (private val userDAO: UserDAO, private val taskDAO:TaskDAO) {
    suspend fun insert(user: User){
        userDAO.insert(user)
    }

     fun getAllUsers(): LiveData<List<User>>{
        return userDAO.getAllUsers()
    }

    fun getUserById(userId: Int): LiveData<User?> {
        return userDAO.getUserById(userId)
    }

    suspend fun update(user: User){
        userDAO.update(user)
    }

    suspend fun delete(user: User){
        userDAO.delete(user)
    }

    suspend fun searchUsers(name: String): List<User>{
        return userDAO.searchUserByName("%$name%")
    }

    suspend fun insertTask(task: Task){
        taskDAO.insertTask(task)
    }

    suspend fun getTaskForUser(userId: Int): List<Task>{
        return taskDAO.getTasksByUserId(userId)
    }

    suspend fun deleteTask(task: Task){
        taskDAO.delete(task)
    }

     fun getAllTasks(): LiveData<List<Task>>{
        return taskDAO.getAllTasks()
    }
}