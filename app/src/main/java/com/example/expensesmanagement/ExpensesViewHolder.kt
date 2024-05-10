package com.example.expensesmanagement

import android.view.View
import android.widget.TextView
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class ExpensesViewHolder(view: View):ViewHolder(view){
    val tvExpenses:TextView = view.findViewById(R.id.tvExpenses)
    val ivDelete :ImageView= view.findViewById(R.id.ivDelete)


}