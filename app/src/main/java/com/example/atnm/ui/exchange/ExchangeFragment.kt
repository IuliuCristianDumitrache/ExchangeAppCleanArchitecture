package com.example.atnm.ui.exchange

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atnm.R
import com.example.atnm.databinding.FragmentExchangeBinding
import com.example.atnm.extensions.disposeIfNotAlready
import com.example.atnm.extensions.observe
import com.example.atnm.network.rxmessages.ReloadExchangeRates
import com.example.atnm.utils.RxBus
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable

@AndroidEntryPoint
class ExchangeFragment : Fragment() {

    private val viewModel: ExchangeViewModel by viewModels()
    private var views: FragmentExchangeBinding? = null
    private var reloadExchangeRateSubscriber: Disposable? = null

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

        initSubscriber()
    }

    private fun initSubscriber() {
        reloadExchangeRateSubscriber = RxBus.listen(ReloadExchangeRates::class.java).subscribe {
            if (it.reload) {
                viewModel.getExchangeList()
            }
        }
    }

    private fun initRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(context)
        views?.exchanges?.layoutManager = linearLayoutManager
        views?.exchanges?.adapter = exchangeAdapter
    }

    override fun onDestroy() {
        reloadExchangeRateSubscriber?.disposeIfNotAlready()
        super.onDestroy()
    }
}