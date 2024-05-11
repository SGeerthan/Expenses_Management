package com.example.expensesmanagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.expensesmanagement.ViewModel.ExpenesesDatas
import com.example.expensesmanagement.database.Expense
import com.example.expensesmanagement.database.ExpensesDatabase
import com.example.expensesmanagement.database.ExpensesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: ExpensesAdapter
    private lateinit var viewModel : ExpenesesDatas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recylerView:RecyclerView = findViewById(R.id.rvExpenses)
        val repository = ExpensesRepository(ExpensesDatabase.getInstance(this))
        viewModel = ViewModelProvider(this)[ExpenesesDatas::class.java]

        viewModel.data.observe(this){
            adapter = ExpensesAdapter(it,repository,viewModel)
            recylerView.adapter = adapter
            recylerView.layoutManager = LinearLayoutManager(this)
        }

        CoroutineScope(Dispatchers.IO).launch {
            val data = repository.getAllExpenses()

            runOnUiThread(){
                viewModel.setData(data)
                val totalAmount = calculateTotal(data)
                val bundle = Bundle()
                bundle.putInt("TOTAL_AMOUNT", totalAmount)

            }
        }

        val addExpenses  : Button = findViewById(R.id.btnAddExpense)

        addExpenses.setOnClickListener{
            displayAlert(repository)
        }

        val btnpg2 = findViewById<Button>(R.id.totalBtn)
        btnpg2.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val expenses = repository.getAllExpenses()
                val totalAmount = calculateTotal(expenses)
                val intent = Intent(this@MainActivity, Page2::class.java)
                intent.putExtra("TOTAL_AMOUNT", totalAmount)
                startActivity(intent)
            }
        }
    
    }

    private fun calculateTotal(expenses: List<Expense>): Int {
        var total = 0
        expenses.forEach {
            total += it.amount
        }
        return total
    }

    private fun displayAlert(repository:ExpensesRepository){
        val builder = AlertDialog.Builder(this)
        builder.setTitle(getText(R.string.alertTitle))
        builder.setMessage("Enter the expense and amount : ")

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val input = EditText(this)
        input.hint = "Expense"
        layout.addView(input)

        val inputAmount = EditText(this)
        inputAmount.hint = "Amount"
        inputAmount.inputType = InputType.TYPE_CLASS_NUMBER
        layout.addView(inputAmount)

        builder.setView(layout)

        builder.setPositiveButton("OK"){dialog,which->
            val expense = input.text.toString()
            val amount = inputAmount.text.toString().toIntOrNull() ?: 0
            CoroutineScope(Dispatchers.IO).launch {
                repository.insert(Expense(expense,amount))
                val data = repository.getAllExpenses()
                runOnUiThread{
                    viewModel.setData(data)
                }
            }

        }
        builder.setNegativeButton("Cancel"){
                dialog,which->
            dialog.cancel()
        }

        val alertDialog = builder.create()
        alertDialog.show()
    }
}
