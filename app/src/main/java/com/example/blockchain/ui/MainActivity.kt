package com.example.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.blockchain.R
import com.example.blockchain.adapter.EntityAdapter
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.helper.MySharedPreferency
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.viewmodel.CurrencyViewModel
import com.example.blockchain.viewmodel.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyDao: CurrencyDao ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.applicationContext as MyApp).currencyComponent?.injectMain(this)

        setRV()
        populateUI()
    }

    private fun setRV(){
        currencyRV.setHasFixedSize(true)
    }

    private fun populateUI(){
        val currencyFactory = CurrencyViewModelFactory(currencyDao!!)
        val viewModel = ViewModelProviders.of(this, currencyFactory).get(CurrencyViewModel::class.java)
        viewModel.getEntityList().observe(this, Observer {
            currencyRV.adapter = EntityAdapter(it)
        })
    }
}
