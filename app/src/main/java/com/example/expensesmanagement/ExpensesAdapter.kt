package com.example.expensesmanagement

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.example.expensesmanagement.ViewModel.ExpenesesDatas
import com.example.expensesmanagement.database.Expense
import com.example.expensesmanagement.database.ExpensesRepository

class ExpensesAdapter(items:List<Expense>,repository: ExpensesRepository,viewModel: ExpenesesDatas): RecyclerView.Adapter<ExpensesViewHolder>() {
    var context:Context?=null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewexpenses,parent,false)
        context = parent.context

        return ExpensesViewHolder(view)

    }

    override fun getItemCount(): Int {
        return items.size

    }

    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.tvExpenses.text = items.get(position).item
        holder.ivDelete.setOnClickListener{
            val expenses = holder.tvExpenses.text

        }
    }
}