package com.aura.data.dataservice.appservice

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build

class DataSourceImpl(
    private val connectivityManager: ConnectivityManager
) : DataSource {
    override fun isInternetAvailable(): Boolean {
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {
                val cap = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork) ?: return false
                return cap.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            }
            else -> {
                val networks = connectivityManager.allNetworks
                for (n in networks) {
                    val nInfo = connectivityManager.getNetworkInfo(n)
                    if (nInfo != null && nInfo.isConnected) return true
                }
            }
        }
        return false
    }
}