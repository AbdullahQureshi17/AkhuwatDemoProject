package com.example.akhuwatdemo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.akhuwatdemo.model.ExpenseSource
import com.example.akhuwatdemo.model.IncomeSource
import com.example.akhuwatdemo.model.User


@Database(entities = [User::class, IncomeSource::class, ExpenseSource::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun sourceDaoIncome(): IncomeSourceDao
    abstract fun sourceDaoExpense(): ExpenseSourceDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                synchronized(this){
                    instance = Room.databaseBuilder(context, AppDatabase::class.java, "budget_database")
                        .allowMainThreadQueries()
                        .build()
                }

            }
            return instance!!
        }
    }


}