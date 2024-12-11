package com.example.roomdatabasedemo

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "task_table",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = ForeignKey.CASCADE
    )]
)

data class Task(
    @PrimaryKey(autoGenerate = true) val id:Int =0,
    val userId: Int,
    val taskName: String
)