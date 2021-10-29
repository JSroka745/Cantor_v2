package com.example.cantor.ui.main.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
class Adapter( private var dataList: List<ListItem>, val itemClickHandler: (ListItem) -> Unit) : RecyclerView.Adapter<Adapter.Viewholder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {


        val view:View
        if(viewType == ListItem.TYPE_HEADER)
        {
            view = LayoutInflater.from(parent.context).inflate(com.example.cantor.R.layout.header_view, parent, false)
        }
        else{
             view = LayoutInflater.from(parent.context).inflate(com.example.cantor.R.layout.card_view, parent, false)
        }



        return Viewholder(view)
    }


    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var type= getItemViewType(position)

        if(type == ListItem.TYPE_HEADER)
        {
            var headeritem: ListItem.HeaderItem= dataList.get(position) as ListItem.HeaderItem
            holder.tv_date.text=headeritem.header_title
            holder.itemView.setOnClickListener { itemClickHandler(headeritem) }
        }
        else{
            var rateitem: ListItem.RatesItem= dataList.get(position) as ListItem.RatesItem
            holder.tv_name.text=rateitem.name
            holder.tv_value.text= rateitem.value.toString()
            holder.itemView.setOnClickListener { itemClickHandler(rateitem) }
        }
            }

    override fun getItemCount(): Int {
        return dataList.size;
    }
    class Viewholder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var tv_name: TextView = itemView.findViewById(com.example.cantor.R.id.tv_title)
        var tv_value: TextView = itemView.findViewById(com.example.cantor.R.id.tv_desc)
        var tv_date: TextView = itemView.findViewById(com.example.cantor.R.id.tv_title)


    }

    override fun getItemViewType(position: Int): Int {
        return dataList.get(position).type()
    }




}