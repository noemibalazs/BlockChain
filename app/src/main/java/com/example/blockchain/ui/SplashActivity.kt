package com.example.blockchain.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.example.blockchain.R
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.Crypto
import com.example.blockchain.data.CryptoCurrencies
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.data.Rates
import com.example.blockchain.network.CurrencyService
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.util.KEY
import com.example.blockchain.util.updateRates
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyService: CurrencyService?=null

    @Inject
    @JvmField
    var currencyDao: CurrencyDao ?=null

    private var myCrypto:Crypto ?=null
    private var cryptoRates:Rates ?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_splash)

        (this.applicationContext as MyApp).currencyComponent?.injectSplash(this)

        getCurrencyList()
        getCryptoCurrencyRates()
        addEntities2DB(myCrypto, cryptoRates)

        Handler().postDelayed({
            launchMainActivity()
        }, 5000)
    }

    private fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun insertCurrencyList(crypto: Crypto){
        val myEntityList = mutableListOf<CurrencyEntity>()
        myEntityList.add(crypto.bch)
        myEntityList.add(crypto.btc)
        myEntityList.add(crypto.eth)
        myEntityList.add(crypto.ltc)
        doAsync {
            currencyDao?.addCurrencyList(myEntityList)
        }
    }

    private fun getCurrencyList(){
        val request = currencyService?.getCryptoCurrencyList(KEY)
        request?.enqueue(object : Callback<CryptoCurrencies>{

            override fun onFailure(call: Call<CryptoCurrencies>, th: Throwable) {
                Log.d("SplashActivity", "onFailure currencyList message: ${th.message}")
            }

            override fun onResponse(call: Call<CryptoCurrencies>, response: Response<CryptoCurrencies>) {
                if (!response.isSuccessful){
                    Log.d("SplashActivity", "onResponse currencyList failure: ${response.code()}")
                }
                if (response.body()!=null && response.isSuccessful){
                    val crypto = response.body()?.crypto
                    myCrypto = crypto
                    Log.d("SplashActivity", "onResponse currencyList isSuccess")
                }
            }
        })
    }

    private fun getCryptoCurrencyRates(){
        val request = currencyService?.getCryptoCurrencyRates(KEY)
        request?.enqueue(object : Callback<Rates>{

            override fun onFailure(call: Call<Rates>, th: Throwable) {
                Log.d("SplashActivity", "onFailure currencyRates message: ${th.message}")
            }

            override fun onResponse(call: Call<Rates>, response: Response<Rates>) {
                if(!response.isSuccessful){
                    Log.d("SplashActivity", "onResponse currencyRates failure: ${response.code()}")
                }

                if(response.body()!=null && response.isSuccessful){
                    val rates = response.body()
                    cryptoRates = rates
                    Log.d("SplashActivity", "onResponse currencyRates isSuccess")
                }
            }
        })
    }

    private fun addEntities2DB(crypto: Crypto?, rates: Rates?){
        crypto?.updateRates(rates)
        crypto?.let {
            insertCurrencyList(it)
        }
        crypto?.let {
            Log.d("SplashActivity", "list were inserted")
        }
        rates?.let {
            Log.d("SplashActivity", "the rates were inserted")
        }
    }
}
