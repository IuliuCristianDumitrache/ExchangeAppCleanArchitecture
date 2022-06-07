package com.example.atnm.ui.exchange

import androidx.recyclerview.widget.DiffUtil
import com.example.atnm.models.Rates

class ExchangeDiffCallback : DiffUtil.ItemCallback<Rates>() {

    override fun areItemsTheSame(oldItem: Rates, newItem: Rates): Boolean =
        oldItem::class.java.simpleName == newItem::class.java.simpleName

    override fun areContentsTheSame(oldItem: Rates, newItem: Rates): Boolean =
        oldItem == newItem
}
