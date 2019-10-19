package com.example.blockchain.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.blockchain.R
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.TimeFrame
import com.example.blockchain.data.TimeFrameRates
import com.example.blockchain.helper.MySharedPreferency
import com.example.blockchain.network.CurrencyService
import com.example.blockchain.util.*
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import kotlinx.android.synthetic.main.activity_graph.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class GraphActivity : AppCompatActivity() {

    @Inject
    @JvmField
    var currencyService: CurrencyService? = null

    @Inject
    @JvmField
    var mySharedPref: MySharedPreferency? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)

        (this.applicationContext as MyApp).currencyComponent?.injectGraph(this)

        populateUI()
        getCurrencyRates()
    }

    private fun populateUI(){
        val symbol = mySharedPref?.getCurrencySymbol()
        graphTitle.text = String.format(getString(R.string.graph_title),symbol)
    }

    private fun populateGraph(rates: List<Double>){
        val myDataPoint = arrayOf(DataPoint(0.0, rates[0]), DataPoint(1.0, rates[1]),DataPoint(2.0, rates[2]), DataPoint(3.0, rates[3]) ,
            DataPoint(4.0, rates[4]),DataPoint(5.0, rates[5]) )
        graph.addSeries(LineGraphSeries(myDataPoint))
    }

    private fun getCurrencyRates() {
        val symbol = mySharedPref?.getCurrencySymbol()
        val request =
            currencyService?.getCryptoCurrencyTimeFrameRates(KEY, START_DATE, END_DATE, symbol!!)
        request?.enqueue(object : Callback<TimeFrameRates>{
            override fun onFailure(call: Call<TimeFrameRates>, th: Throwable) {
                Log.d("GraphActivity", "onFailure message: ${th.message}")
            }

            override fun onResponse(call: Call<TimeFrameRates>, response: Response<TimeFrameRates>) {
                if (!response.isSuccessful){
                    Log.d("GraphActivity", "onResponse failure ${response.code()}")
                }

                if (response.body()!= null && response.isSuccessful){
                    response.body()?.let {
                        val timeRates = it.rates
                        val currencyRates = getListRates(timeRates)
                        populateGraph(currencyRates)

                        Log.d("GraphActivity", "onResponse success")
                    }
                }
            }

        })
    }

    private fun getListRates(timeFrame: TimeFrame): MutableList<Double> {
        val symbol = mySharedPref?.getCurrencySymbol()
        return when(symbol!!){
            BTC -> {
                val monday: Double = timeFrame.monday.BTC
                val tuesday:Double = timeFrame.tuesday.BTC
                val wednesday = timeFrame.wednesday.BTC
                val thursday: Double = timeFrame.thursday.BTC
                val friday: Double = timeFrame.friday.BTC
                val saturday: Double = timeFrame.saturday.BTC
                mutableListOf(monday, tuesday, wednesday, thursday, friday, saturday)
            }
            BCH -> {
                val monday: Double = timeFrame.monday.BCH
                val tuesday:Double = timeFrame.tuesday.BCH
                val wednesday = timeFrame.wednesday.BCH
                val thursday: Double = timeFrame.thursday.BCH
                val friday: Double = timeFrame.friday.BCH
                val saturday: Double = timeFrame.saturday.BCH
                mutableListOf(monday, tuesday, wednesday, thursday, friday, saturday)
            }
            ETH -> {
                val monday: Double = timeFrame.monday.ETH
                val tuesday:Double = timeFrame.tuesday.ETH
                val wednesday = timeFrame.wednesday.ETH
                val thursday: Double = timeFrame.thursday.ETH
                val friday: Double = timeFrame.friday.ETH
                val saturday: Double = timeFrame.saturday.ETH
                mutableListOf(monday, tuesday, wednesday, thursday, friday, saturday)
            }
            else ->{
                val monday: Double = timeFrame.monday.LTC
                val tuesday:Double = timeFrame.tuesday.LTC
                val wednesday = timeFrame.wednesday.LTC
                val thursday: Double = timeFrame.thursday.LTC
                val friday: Double = timeFrame.friday.LTC
                val saturday: Double = timeFrame.saturday.LTC
                mutableListOf(monday, tuesday, wednesday, thursday, friday, saturday)
            }
        }

    }

}
