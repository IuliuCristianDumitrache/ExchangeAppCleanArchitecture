package com.example.atnm.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.atnm.R
import com.example.atnm.databinding.FragmentExchangeBinding
import com.example.atnm.extensions.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeFragment : Fragment() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var views: FragmentExchangeBinding? = null

    private val exchangeAdapter: ExchangeAdapter = ExchangeAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_exchange, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        views = FragmentExchangeBinding.bind(view)

        viewModel.getExchangeList()

        initRecyclerView()

        observe(viewModel.isLoadingInProgress) { visible ->
            views?.photoLoadingProgress?.isVisible = visible
        }

        observe(viewModel.exchangeList) { exchangeList ->
            viewModel.getMissingExchanges(exchangeList)
        }

        observe(viewModel.completeExchangeList) { completeExchangeList ->
            exchangeAdapter.submitList(completeExchangeList)
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        views?.exchanges?.layoutManager = linearLayoutManager
        views?.exchanges?.adapter = exchangeAdapter
    }
}