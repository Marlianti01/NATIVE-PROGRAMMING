package com.example.roomdatabasedemo

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userViewModel = ViewModelProvider(this)[UserViewModel::class.java]

        val searchView = findViewById<SearchView>(R.id.searchView)
        val addButton = findViewById<Button>(R.id.buttonAdd)
        val deleteButton = findViewById<Button>(R.id.buttonDelete)
        val updateButton = findViewById<Button>(R.id.buttonUpdate)

        val nameInput = findViewById<EditText>(R.id.editTextName)
        val ageInput = findViewById<EditText>(R.id.editTextAge)
        val userIdInput = findViewById<EditText>(R.id.delete)

        val recyclerView = findViewById<RecyclerView>(R.id.recycleView)
        val taskRecyclerView = findViewById<RecyclerView>(R.id.recycleViewTasks)

        adapter = UserAdapter{user -> showAddTask(user)}
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        taskAdapter = TaskAdapter()
        taskRecyclerView.adapter = taskAdapter
        taskRecyclerView.layoutManager = LinearLayoutManager(this)
        // Observing user data changes
        userViewModel.getAllUsers().observe(this) { users ->
            adapter.submitList(users)
            adapter.notifyDataSetChanged()
        }

        userViewModel.allTasks.observe(this) { tasks ->
            Log.d("Tasks Debug", "Tasks List: $tasks")
            taskAdapter.submitList(tasks)
        }

        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let { performSearch(it) }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {performSearch(it)}
                return false
            }
        })

        addButton.setOnClickListener {
            val name = nameInput.text.toString()
            val ageText = ageInput.text.toString()
            if (name.isNotEmpty() && ageText.isNotEmpty()) {
                val age = ageText.toIntOrNull()
                if (age != null) {
                    val user = User(name = name, age = age)
                    userViewModel.insert(user)
                    Toast.makeText(this, "User added successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Enter valid age", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Name or age cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }

        deleteButton.setOnClickListener {
            val userIdText = userIdInput.text.toString()
            val userId = userIdText.toIntOrNull()
            if (userId != null) {
                userViewModel.getUserById(userId).observe(this) { user ->
                    if (user != null) {
                        userViewModel.delete(user)
                        Toast.makeText(this, "User deleted", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Enter valid user ID", Toast.LENGTH_SHORT).show()
            }
        }

        updateButton.setOnClickListener {
            val userIdText = userIdInput.text.toString()
            val userId = userIdText.toIntOrNull()
            val updateName = findViewById<EditText>(R.id.updateName).text.toString()
            val updateAgeText = findViewById<EditText>(R.id.updateAge).text.toString()
            val updateAge = updateAgeText.toIntOrNull()

            if (userId != null && updateName.isNotEmpty() && updateAge != null) {
                userViewModel.getUserById(userId).observe(this) { user ->
                    if (user != null) {
                        val updatedUser = user.copy(name = updateName, age = updateAge)
                        userViewModel.update(updatedUser)
                        Toast.makeText(this, "User updated successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Invalid data entered", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun performSearch(query: String){
        userViewModel.searchUsers(query).observe(this) { users ->
            adapter.submitList(users)
        }
    }

    private fun showAddTask(user: User){
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_task, null)
        val editTaskName = dialogView.findViewById<EditText>(R.id.editTextTaskName)

        AlertDialog.Builder(this)
            .setTitle("Add Task for id: ${user.id} name:${user.name}")
            .setView(dialogView)
            .setPositiveButton("Add") {_, _ ->
                val taskName = editTaskName.text.toString()
                val task = Task(userId = user.id, taskName = taskName)
                userViewModel.insertTask(task)
                Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
