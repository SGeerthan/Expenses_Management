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
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
        holder.cdExpense.text = items.get(position).expense
        holder.tvAmount.text = items[position].amount.toString()
        holder.ivDelete.setOnClickListener{
            val isChecked = holder.cdExpense.isChecked
            if(isChecked){
                CoroutineScope(Dispatchers.IO).launch {
                    repository.delete(items.get(position))
                    val data = repository.getAllExpenses()
                    withContext(Dispatchers.Main){
                        viewModel.setData(data)
                    }
                }
                Toast.makeText(context,"Expenses Deleted",Toast.LENGTH_LONG).show()
            }else{
                Toast.makeText(context,"Select the item to delete",Toast.LENGTH_LONG).show()
            }

        }
    }
}