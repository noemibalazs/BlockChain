package com.example.blockchain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.room.CurrencyDao

class CurrencyViewModel(val dao: CurrencyDao) : ViewModel() {

     fun getEntityList(): LiveData<MutableList<CurrencyEntity>>{
         return dao.getCurrencyList()
     }

     fun getEntity(symbol: String): LiveData<CurrencyEntity>{
         return  dao.getCurrency(symbol)
     }
}