package com.example.expensesmanagement.database

class ExpensesRepository (
    private val db:ExpensesDatabase
){
    suspend fun insert(expenses:Expense)=db.getExpenseDao().insert(expenses)
    suspend fun delete(expenses:Expense)=db.getExpenseDao().delete(expenses)

    fun getAllExpenses():List<Expense> = db.getExpenseDao().getAllExpenses()

}