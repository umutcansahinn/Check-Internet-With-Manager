package com.umutcansahin.checkinternetpermissionwithmanager

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import androidx.lifecycle.LiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

enum class Status {
    Available, Unavailable, Lost
}

@Singleton
class NetworkManager @Inject constructor(
    @ApplicationContext context: Context
) : LiveData<Status>() {


    override fun onActive() {
        super.onActive()
        checkNetworkConnectivity()
    }

    override fun onInactive() {
        super.onInactive()
        connectivityManager.unregisterNetworkCallback(networkCallback)
    }

    private var connectivityManager =
        context.applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onAvailable(network: Network) {
            super.onAvailable(network)
            postValue(Status.Available)
        }

        override fun onUnavailable() {
            super.onUnavailable()
            postValue(Status.Unavailable)
        }

        override fun onLost(network: Network) {
            super.onLost(network)
            postValue(Status.Lost)
        }
    }

    private fun checkNetworkConnectivity() {
        val network = connectivityManager.activeNetwork
//        if (network == null) {
//            postValue(false)
//        }
        val requestBuilder = NetworkRequest.Builder().apply {
            addCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
        }.build()

        connectivityManager.registerNetworkCallback(requestBuilder, networkCallback)
    }
}