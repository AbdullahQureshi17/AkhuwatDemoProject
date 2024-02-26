package com.example.akhuwatdemo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Income")
data class IncomeSource(@PrimaryKey(autoGenerate = true)
    val id: Int,
    val userOwnerId: Int?,
    @ColumnInfo(name = "income") var income: String?,
    @ColumnInfo(name = "income value") var incomeValue: Int?,

)
