package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name)
        ageEditText = findViewById(R.id.et_age)
        cityEditText = findViewById(R.id.et_city)

        saveButton = findViewById(R.id.btnSave)
        loadButton = findViewById(R.id.btn_load)
        clearButton = findViewById(R.id.btn_clear)

        saveButton.setOnClickListener{
            saveName()
        }

        loadButton.setOnClickListener{
            loadName()
        }

        clearButton.setOnClickListener{
            clear()
        }
    }

    private fun saveName(){



        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val city = cityEditText.text.toString()

        var isValid = true

        if(name.isEmpty()){
            nameEditText.error = "Please enter your name"
            isValid = false
        }

        if(age.isEmpty()){
            ageEditText.error = "Please enter your age"
            isValid = false
        }

        if(city.isEmpty()){
            cityEditText.error = "Please enter your city"
            isValid = false
        }

        if(isValid){
            val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("userName", name)
            editor.putString("userAge", age)
            editor.putString("userCity", city)
            editor.apply()

            greetingTextView.text = "Data saved!!"
        }

    }

    private fun loadName(){
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val savedName = sharedPreferences.getString("userName", "No name saved")

        greetingTextView.text = "Welcome, $savedName!"
    }

    private fun clear(){
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        editor.clear()
        editor.apply()

        greetingTextView.text = "Data Cleared"
        nameEditText.text.clear()
        ageEditText.text.clear()
        cityEditText.text.clear()


    }
}