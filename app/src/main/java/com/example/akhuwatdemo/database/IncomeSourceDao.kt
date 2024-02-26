package com.example.akhuwatdemo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.akhuwatdemo.model.IncomeSource

@Dao
interface IncomeSourceDao {
    @Insert
    suspend fun insertSource(vararg source: IncomeSource)

    @Query("SELECT * FROM Income WHERE userOwnerId LIKE :userOwnerId")
    fun getAllIncomeValues(userOwnerId : Int?): List<IncomeSource>

    @Query("SELECT * FROM Income")
    fun getAllIncomeValues2(): List<IncomeSource>


}