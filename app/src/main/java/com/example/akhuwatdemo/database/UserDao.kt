package com.example.akhuwatdemo.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.akhuwatdemo.model.User


@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    suspend fun getAll(): List<User>

//    @Query("SELECT user_id FROM users")
//    suspend fun getUserId(): List<Long>

    @Query("SELECT EXISTS (SELECT * FROM users WHERE user_name LIKE :username)")
    fun isTaken(username: String): Boolean

    @Query("SELECT EXISTS (SELECT * FROM users WHERE user_name LIKE :username AND password LIKE :password)")
     fun login(username: String , password : String): Boolean

    @Query("SELECT * FROM users WHERE user_name LIKE :username AND password LIKE :password")
    fun getUser(username: String , password : String): User

//    @Transaction
//    @Query("SELECT * FROM users")
//    fun getUsersAndIncomes(): List<IncomeWithUser>



    @Insert
    suspend fun insertUser(vararg users: User)


    @Delete
    suspend fun delete(user: User)

}