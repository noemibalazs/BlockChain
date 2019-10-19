package com.example.blockchain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.blockchain.room.CurrencyDao

class CurrencyViewModelFactory(val dao: CurrencyDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
       return CurrencyViewModel(dao) as T
    }

}