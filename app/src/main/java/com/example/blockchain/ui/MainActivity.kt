package com.example.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.blockchain.R
import com.example.blockchain.adapter.EntityAdapter
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.viewmodel.CurrencyViewModel
import com.example.blockchain.viewmodel.CurrencyViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyDao: CurrencyDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (this.applicationContext as MyApp).currencyComponent?.injectMain(this)

        setRV()
        populateUI()
    }

    private fun setRV() {
        currencyRV.setHasFixedSize(true)
    }


    private fun populateUI(){
        val factory = CurrencyViewModelFactory(currencyDao!!)
        val currencyVM = ViewModelProviders.of(this, factory).get(CurrencyViewModel::class.java)
        currencyVM.getEntityList().observe(
            this,
            object : Observer<MutableList<CurrencyEntity>> {
                override fun onChanged(list: MutableList<CurrencyEntity>?) {
                    currencyVM.getEntityList().removeObserver(this)
                    list?.let {
                        currencyRV.adapter = EntityAdapter(it)
                    }
                }
            })
    }
}
