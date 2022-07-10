package be.msdc.hypco.api.reporter

import android.util.Log
import be.msdc.hypco.internal.data.repository.RepositoryProvider
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class IHypCoReporter {
    var callback: Callback? = null

    internal val repository by lazy { RepositoryProvider.items() }
    internal val errorHandler = CoroutineExceptionHandler { _, throwable ->
        callback?.onError(throwable) ?: Log.e("IHypCoReporter", "Error in HypCoReporter", throwable)
    }

    fun interface Callback {
        fun onError(error: Throwable)
    }
}