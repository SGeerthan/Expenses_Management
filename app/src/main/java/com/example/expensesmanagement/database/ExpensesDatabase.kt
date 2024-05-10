package com.example.expensesmanagement.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Expense::class], version = 1)
abstract class ExpensesDatabase:RoomDatabase() {
    abstract fun getExpenseDao():ExpensesDao

    companion object{
        @Volatile
        private var INSTANCE:ExpensesDatabase?=null

        fun getInstance(context: Context):ExpensesDatabase{
            synchronized(this){
                return INSTANCE?: Room.databaseBuilder(
                    context,
                    ExpensesDatabase::class.java,
                    "expense_db"
                ).build().also{
                    INSTANCE = it
                }
            }
        }
    }
}