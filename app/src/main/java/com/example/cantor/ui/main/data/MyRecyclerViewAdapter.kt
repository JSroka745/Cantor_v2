package com.example.cantor.ui.main.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyRecyclerViewAdapter( private var dataList: MutableList<ListItem>, val itemClickHandler: (ListItem.RatesItem) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view:View
        if(viewType == ListItem.TYPE_HEADER)
        {
            view = LayoutInflater.from(parent.context).inflate(com.example.cantor.R.layout.header_view, parent, false)
            return Headerviewholder(view)
        }
        else{
             view = LayoutInflater.from(parent.context).inflate(com.example.cantor.R.layout.card_view, parent, false)
            view.setOnClickListener(View.OnClickListener {

            })
            return Rateviewholder(view)
        }
    }


    override fun getItemCount(): Int {
        return dataList.size;
    }
     class Headerviewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_date: TextView = itemView.findViewById(com.example.cantor.R.id.tv_header_title)

    }

    class Rateviewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var tv_name: TextView = itemView.findViewById(com.example.cantor.R.id.tv_rates_title)
        var tv_value: TextView = itemView.findViewById(com.example.cantor.R.id.tv_rates_desc)

    }

    override fun getItemViewType(position: Int): Int {
        return dataList[position].type()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        if(holder is Headerviewholder)
        {

            var headeritem: ListItem.HeaderItem= dataList[position] as ListItem.HeaderItem
            holder.tv_date.text=headeritem.header_title

        }
        else if(holder is Rateviewholder){
            var rateitem: ListItem.RatesItem= dataList[position] as ListItem.RatesItem
            holder.itemView.setOnClickListener { itemClickHandler(rateitem) }

            if(rateitem.name=="GBP")
            {
                holder.tv_value.text= rateitem.value.toString()+"£"
            }

            else if(rateitem.name=="USD" || rateitem.name=="MXN" || rateitem.name=="AUD" || rateitem.name=="CAD")
            {
                holder.tv_value.text= rateitem.value.toString()+"$"
            }

            else if(rateitem.name=="JPY")
            {
                holder.tv_value.text= rateitem.value.toString()+"¥"
            }


            else if(rateitem.name=="PLN")
            {
                holder.tv_value.text= rateitem.value.toString()+"zł"
            }

            else if(rateitem.name=="RUB")
            {
                holder.tv_value.text= rateitem.value.toString()+"₽"
            }
            holder.tv_name.text=rateitem.name


        }

    }


}