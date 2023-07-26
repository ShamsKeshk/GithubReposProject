package com.example.githuprepo.networkLayer.exceptions

import androidx.annotation.StringRes
import java.io.IOException

class NetworkConnectivityException(@StringRes private val errorMessageResourceId: Int)
    : IOException() {
}