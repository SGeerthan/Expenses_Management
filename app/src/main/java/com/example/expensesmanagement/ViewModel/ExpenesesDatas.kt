package com.example.expensesmanagement.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.expensesmanagement.database.Expense

class ExpenesesDatas : ViewModel(){
    private val _data = MutableLiveData<List<Expense>>()

    val data:LiveData<List<Expense>> = _data

    fun setData(data:List<Expense>){
        _data.value = data
    }

}