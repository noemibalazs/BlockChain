package com.example.blockchain.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.example.blockchain.R
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.*
import com.example.blockchain.network.CurrencyService
import com.example.blockchain.room.CurrencyDao
import com.example.blockchain.util.*
import org.jetbrains.anko.doAsync
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyService: CurrencyService? = null

    @Inject
    @JvmField
    var currencyDao: CurrencyDao? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        (this.applicationContext as MyApp).currencyComponent?.injectSplash(this)

        getCurrencyList()
        getCryptoCurrencyRates()

        Handler().postDelayed({
            launchMainActivity()
        }, 5000)
    }

    private fun launchMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
        Log.d("Splash", "open Main activity")
    }

    private fun insertCurrencyList(crypto: Crypto) {
        val myEntityList = mutableListOf<CurrencyEntity>()
        myEntityList.add(crypto.bch)
        myEntityList.add(crypto.btc)
        myEntityList.add(crypto.eth)
        myEntityList.add(crypto.ltc)
        doAsync {
            currencyDao?.addCurrencyList(myEntityList)
        }
    }

    private fun getCurrencyList() {
        val request = currencyService?.getCryptoCurrencyList(KEY)
        request?.enqueue(object : Callback<CryptoCurrencies> {

            override fun onFailure(call: Call<CryptoCurrencies>, th: Throwable) {
                Log.d("SplashActivity", "onFailure currencyList message: ${th.message}")
            }

            override fun onResponse(
                call: Call<CryptoCurrencies>,
                response: Response<CryptoCurrencies>
            ) {
                if (!response.isSuccessful) {
                    Log.d("SplashActivity", "onResponse currencyList failure: ${response.code()}")
                }
                if (response.body() != null && response.isSuccessful) {
                    response.body()?.let {
                        val crypto = it.crypto
                        val myCrypto = Crypto(crypto.bch, crypto.btc, crypto.eth, crypto.ltc)
                        insertCurrencyList(myCrypto)
                        Log.d("SplashActivity", "onResponse currencyList isSuccess $myCrypto")
                    }
                }
            }
        })

    }

    private fun getCryptoCurrencyRates(){
        val request = currencyService?.getCryptoCurrencyRates(KEY)
        request?.enqueue(object : Callback<CurrencyRates>{

            override fun onResponse(call: Call<CurrencyRates>, response: Response<CurrencyRates>) {
                if (!response.isSuccessful) {
                    Log.d("SplashActivity", "onResponse currencyRates failure: ${response.code()}")
                }

                if (response.isSuccessful && response.body()!=null){
                    response.body()?.let {
                        val rates = it.rates
                        val cryptoRates = Rates(rates.BCH, rates.BTC, rates.ETH, rates.LTC)
                        updateBTC(cryptoRates)
                        updateBCH(cryptoRates)
                        updateETH(cryptoRates)
                        updateLTC(cryptoRates)
                        Log.d("SplashActivity", "onResponse is success")
                    }
                }
            }
            override fun onFailure(call: Call<CurrencyRates>, th: Throwable) {
                Log.d("SplashActivity", "onFailure currencyRates message: ${th.message}")
            }
        })
    }

    private fun btcEntity(entity: CurrencyEntity, rate: Rates){
        entity.rate = rate.BTC
        doAsync {
            currencyDao?.addEntity2DB(entity)
        }
    }

    private fun updateBTC(rate: Rates){
        currencyDao?.getCurrency(BTC)!!.observe(this, object:Observer<CurrencyEntity>{
            override fun onChanged(entity: CurrencyEntity?) {
                entity?.let {
                    btcEntity(it, rate)
                }
            }
        })
    }

    private fun bchEntity(entity: CurrencyEntity, rate: Rates){
        entity.rate = rate.BCH
        doAsync {
            currencyDao?.addEntity2DB(entity)
        }
    }

    private fun updateBCH(rate: Rates){
        currencyDao?.getCurrency(BCH)!!.observe(this, object:Observer<CurrencyEntity>{
            override fun onChanged(entity: CurrencyEntity?) {
                entity?.let {
                    bchEntity(it, rate)
                }
            }
        })
    }

    private fun ethEntity(entity: CurrencyEntity, rate: Rates){
        entity.rate = rate.ETH
        doAsync {
            currencyDao?.addEntity2DB(entity)
        }
    }

    private fun updateETH(rate: Rates){
        currencyDao?.getCurrency(ETH)!!.observe(this, object:Observer<CurrencyEntity>{
            override fun onChanged(entity: CurrencyEntity?) {
                entity?.let {
                    ethEntity(it, rate)
                }
            }
        })
    }

    private fun ltcEntity(entity: CurrencyEntity, rate: Rates){
        entity.rate = rate.LTC
        doAsync {
            currencyDao?.addEntity2DB(entity)
        }
    }

    private fun updateLTC(rate: Rates){
        currencyDao?.getCurrency(LTC)!!.observe(this, object:Observer<CurrencyEntity>{
            override fun onChanged(entity: CurrencyEntity?) {
                entity?.let {
                   ltcEntity(it, rate)
                }
            }
        })
    }



}
