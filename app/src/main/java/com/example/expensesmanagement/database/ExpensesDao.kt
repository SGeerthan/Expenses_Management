package com.example.expensesmanagement.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ExpensesDao {

    @Insert
    suspend fun insert(expense: Expense)

    @Delete
    suspend fun delete(expense: Expense)

    @Query("SELECT * FROM Expenses")
    fun getAllExpenses():List<Expense>

    @Query("SELECT * FROM Expenses WHERE id =:id ")
    fun getOne(id:Int):Expense

}