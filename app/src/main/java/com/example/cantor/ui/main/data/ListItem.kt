package com.example.cantor.ui.main.data

abstract class ListItem {
    abstract fun type(): Int


    data class HeaderItem(var header_title: String) : ListItem() {

        fun getTittle(): String{
            return header_title
        }




        override fun type(): Int {
            return TYPE_HEADER
        }
    }

    data class RatesItem(var name:String,var value:Double): ListItem() {



        override fun type(): Int {
            return TYPE_RATE
        }
    }

    companion object {
        const val TYPE_HEADER = 0
        const val TYPE_RATE = 1
    }
}