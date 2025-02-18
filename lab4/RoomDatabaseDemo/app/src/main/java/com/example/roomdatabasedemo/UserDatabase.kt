package com.example.roomdatabasedemo

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [User::class, Task::class], version = 2, exportSchema = false)

abstract class UserDatabase : RoomDatabase(){
    abstract fun userDao(): UserDAO
    abstract fun taskDao(): TaskDAO

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"
                ).build()
                INSTANCE =instance
                instance
            }
        }
    }
}