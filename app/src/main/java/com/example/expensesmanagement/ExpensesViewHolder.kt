package com.example.expensesmanagement

import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ExpensesViewHolder(view: View):ViewHolder(view){
    val cdExpense:CheckBox = view.findViewById(R.id.cbExpenses)
    val ivDelete :ImageView= view.findViewById(R.id.ivDelete)
    val tvAmount: TextView = view.findViewById(R.id.tvAmount)


}