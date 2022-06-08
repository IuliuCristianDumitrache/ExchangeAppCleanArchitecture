package com.example.atnm.ui.exchange

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.atnm.R
import com.example.atnm.databinding.ExchangeListItemBinding
import com.example.atnm.models.Rates

class ExchangeAdapter() :
    ListAdapter<Rates, RecyclerView.ViewHolder>(ExchangeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = when (viewType) {
            R.layout.exchange_list_item -> ExchangeListItemBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
            else -> {
                ExchangeListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }

        return ExchangeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = currentList[position]
        when (holder) {
            is ExchangeViewHolder -> {
                holder.bind(item)
            }
        }
    }
}