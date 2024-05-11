package com.example.expensesmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.expensesmanagement.database.Expense

class Page2 : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)

        val totalAmount = intent.getIntExtra("TOTAL_AMOUNT", 0)
        val totalAmountTextView: TextView = findViewById(R.id.totalAmountTextView)
        totalAmountTextView.text = "Total Expenses \n\t\t Rs.$totalAmount"

    }

}