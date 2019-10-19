package com.example.blockchain.adapter

import android.content.Context
import android.icu.util.ValueIterator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.blockchain.R
import com.example.blockchain.application.MyApp
import com.example.blockchain.data.CurrencyEntity
import com.example.blockchain.helper.MySharedPreferency
import com.example.blockchain.ui.GraphActivity
import com.example.blockchain.util.DebounceClickListener
import com.example.blockchain.util.loadPicture
import com.example.blockchain.util.openActivity
import javax.inject.Inject

class EntityAdapter(val myList: MutableList<CurrencyEntity>) :
    RecyclerView.Adapter<EntityAdapter.EntityVH>() {

    @Inject
    @JvmField
    var mySharedPref: MySharedPreferency? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EntityVH {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false)
        return EntityVH(view)
    }

    override fun getItemCount(): Int {
        return myList.size
    }

    override fun onBindViewHolder(holder: EntityVH, position: Int) {

        mySharedPref = (holder.itemView.context.applicationContext as MyApp).netComponent?.sharedPref()

        val entity = myList[position]
        holder.bind(entity, holder.itemView.context)

        holder.itemView.setOnClickListener(object : DebounceClickListener() {
            override fun onDebounce(v: View) {
                mySharedPref?.saveCurrencySymbol(entity.symbol)
                holder.avatar.context.openActivity(GraphActivity::class.java)
            }
        })
    }

    inner class EntityVH(view: View) : RecyclerView.ViewHolder(view) {
        val avatar = view.findViewById<ImageView>(R.id.currencyAvatar)
        val name = view.findViewById<TextView>(R.id.currencyName)
        val symbol = view.findViewById<TextView>(R.id.currencySymbol)
        val rate = view.findViewById<TextView>(R.id.currencyRate)

        fun bind(entity: CurrencyEntity, context: Context) {
            loadPicture(entity.iconUrl, avatar)
            name.text = entity.name
            symbol.text = entity.symbol
            rate.text = String.format(context.getString(R.string.currency_rate), entity.rate)
        }
    }
}