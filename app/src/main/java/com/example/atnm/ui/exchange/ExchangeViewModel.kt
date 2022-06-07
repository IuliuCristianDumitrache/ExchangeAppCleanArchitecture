package com.example.atnm.ui.exchange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.atnm.R
import com.example.atnm.domainlayer.FetchExchangeUseCase
import com.example.atnm.models.Rates
import com.example.atnm.models.RatesResponse
import com.example.atnm.network.Status.*
import com.example.atnm.network.rxmessages.SnackbarMessage
import com.example.atnm.utils.ConversionUtil
import com.example.atnm.utils.RxBus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(private val fetchExchangeUseCase: FetchExchangeUseCase) :
    ViewModel() {

    private val viewModelJob = Job()
    private val ioScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val isLoadingInProgress = MutableLiveData(false)
    val exchangeList: MutableLiveData<RatesResponse> = MutableLiveData()
    val completeExchangeList: MutableLiveData<ArrayList<Rates>> = MutableLiveData()


    fun getExchangeList() =
        ioScope.launch {
            fetchExchangeUseCase.fetchExchangeData().collect {
                when (it.status) {
                    SUCCESS -> {
                        isLoadingInProgress.postValue(false)
                        exchangeList.postValue(it.data)
                    }
                    ERROR -> {
                        isLoadingInProgress.postValue(false)
                        RxBus.publish(SnackbarMessage(R.string.an_error_has_occured))
                    }
                    LOADING -> {
                        isLoadingInProgress.postValue(true)
                    }
                }
            }
        }

    fun getMissingExchanges(exchangeList: RatesResponse) {
        val completedExchangeList = arrayListOf<Rates>()
        completedExchangeList.addAll(exchangeList.rates)

        ConversionUtil.setRates(exchangeList.rates)

        val missingRates = arrayListOf<Rates>()
        exchangeList.pairs.forEach { pair ->
            missingRates.add(
                Rates(
                    from = pair.from,
                    to = pair.to,
                    rate = ConversionUtil.getExchangeRate(pair.from, pair.to)
                )
            )
        }
        completedExchangeList.addAll(missingRates)

        completeExchangeList.postValue(completedExchangeList)
    }
}