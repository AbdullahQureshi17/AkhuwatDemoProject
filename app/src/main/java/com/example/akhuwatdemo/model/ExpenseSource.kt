package com.example.akhuwatdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Expense")
data class ExpenseSource(@PrimaryKey(autoGenerate = true)
       val id: Int,
        val userOwnerId: Int?,
        @ColumnInfo(name = "expense") var expense: String?,
       @ColumnInfo(name = "expense value") var expenseValue: Int?
    )
