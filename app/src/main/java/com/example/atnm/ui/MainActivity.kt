package com.example.atnm.ui

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.atnm.R
import com.example.atnm.databinding.ActivityMainBinding
import com.example.atnm.network.rxmessages.SnackbarMessage
import com.example.atnm.utils.RxBus
import com.example.atnm.utils.connectivity.NetworkCallback
import com.example.atnm.utils.connectivity.NetworkConnection
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.disposables.Disposable

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val networkConnection = NetworkConnection
    lateinit var views: ActivityMainBinding

    private var subscribers = ArrayList<Disposable>()

    private val networkCallback = object: NetworkCallback(networkConnection) {
        override fun onAvailable(network: Network) {
            //TODO show a snackbar
            super.onAvailable(network)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
        }
    }

    private fun registerNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.registerNetworkCallback(NetworkRequest.Builder().build(), networkCallback)
    }

    private fun unregisterNetworkCallback() {
        val connectivityManager = getSystemService(ConnectivityManager::class.java)
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        views = ActivityMainBinding.inflate(layoutInflater)
        setContentView(views.root)
        registerNetworkCallback()
        initSubscribers()
    }

    private fun initSubscribers() {
        if (subscribers.isNotEmpty()) {
            return
        }

        subscribers.add(RxBus.listen(SnackbarMessage::class.java).subscribe {
            runOnUiThread {
                Snackbar.make(views.navHostFragment, it.messageResId, Snackbar.LENGTH_LONG)
                    .setAction(R.string.dismiss) {
                    }
                    .show()
            }
        })
    }

    override fun onDestroy() {
        unregisterNetworkCallback()
        super.onDestroy()
    }
}