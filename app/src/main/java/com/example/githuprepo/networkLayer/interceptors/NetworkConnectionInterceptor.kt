package com.example.githuprepo.networkLayer.interceptors

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import com.example.githuprepo.R
import com.example.githuprepo.networkLayer.exceptions.NetworkConnectivityException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkConnectionInterceptor(private val context: Context) : Interceptor {

    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) {
            throw NetworkConnectivityException(R.string.str_network_no_internet_connection)
        }
        return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean{
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val currentNetwork = connectivityManager.activeNetwork
            currentNetwork != null
        } else {
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
    }
}