package com.example.akhuwatdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.akhuwatdemo.model.ExpenseSource
import com.example.akhuwatdemo.model.IncomeSource

@Dao
interface ExpenseSourceDao {
    @Insert
    suspend fun insertSource(vararg source: ExpenseSource)

    @Query("SELECT * FROM Expense WHERE userOwnerId LIKE :userOwnerId")
    fun getAllExpenseValues(userOwnerId : Int?): List<ExpenseSource>

    @Query("SELECT * FROM Expense")
    fun getAllExpenseValues(): List<ExpenseSource>
}