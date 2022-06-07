package com.example.atnm.ui.exchange

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.atnm.databinding.ExchangeListItemBinding
import com.example.atnm.models.Rates

class ExchangeViewHolder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(rate: Rates) {
        when (binding) {
            is ExchangeListItemBinding -> {
                val titleText = "To: ${rate.to} \nFrom: ${rate.from}"
                binding.tvTitle.text = titleText
                val rateText = "Rate: ${rate.rate}"
                binding.tvSubtitle.text = rateText
            }
        }
    }
}