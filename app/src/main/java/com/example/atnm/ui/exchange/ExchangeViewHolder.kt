package com.example.atnm.ui.exchange

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.atnm.R
import com.example.atnm.databinding.ExchangeListItemBinding
import com.example.atnm.models.Rates

class ExchangeViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(rate: Rates) {
        when (binding) {
            is ExchangeListItemBinding -> {
                binding.tvTitle.text =
                    itemView.context.getString(R.string.to_from, rate.to, rate.from)
                binding.tvSubtitle.text =
                    itemView.context.getString(R.string.rate, rate.rate.toString())
            }
        }
    }
}