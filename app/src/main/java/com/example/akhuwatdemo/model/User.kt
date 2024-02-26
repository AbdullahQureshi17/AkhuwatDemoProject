package com.example.akhuwatdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

import androidx.room.PrimaryKey


@Entity
    (tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    var userId: Int,
   // @ColumnInfo(name = "user_id") var userId: Long,
    @ColumnInfo(name = "first_name") var firstName: String?,
    @ColumnInfo(name = "last_name") var lastName: String?,
    @ColumnInfo(name = "user_name") var userName: String?,
    @ColumnInfo(name = "password") var password: String?


)
