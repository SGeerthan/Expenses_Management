package com.example.expensesmanagement.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Expenses")
data class Expense(
    var expense:String?,
    var amount: Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}



