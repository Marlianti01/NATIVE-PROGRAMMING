package com.example.roomdatabasedemo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        val taskDao = UserDatabase.getDatabase(application).taskDao()
        repository = UserRepository(userDao, taskDao)
    }

    // Observe all tasks from the database
    val allTasks: LiveData<List<Task>> = repository.getAllTasks()

    // Insert a new user
    fun insert(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(user)
        }
    }

    // Insert a new task
    fun insertTask(task: Task) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertTask(task)
        }
    }

    // Observe all users using LiveData
    fun getAllUsers(): LiveData<List<User>> {
        return repository.getAllUsers()
    }

    // Observe a specific user by ID using repository
    fun getUserById(userId: Int): LiveData<User?> {
        return repository.getUserById(userId)
    }

    // Update a user
    fun update(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(user)
        }
    }

    // Delete a user
    fun delete(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(user)
        }
    }


        // Search users by name
    fun searchUsers(name: String): LiveData<List<User>> {
        return liveData(Dispatchers.IO) {
            emit(repository.searchUsers(name))
        }
    }
}
