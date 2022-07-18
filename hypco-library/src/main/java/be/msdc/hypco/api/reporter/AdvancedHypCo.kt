package be.msdc.hypco.api.reporter

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*
import kotlin.coroutines.CoroutineContext

class AdvancedHypCo internal constructor() : AdvancedSuspendHypCo(), CoroutineScope {

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    fun deleteAll() {
        launch(errorHandler) { deleteAllSuspend() }
    }

    fun deleteAll(threshold: Date) {
        launch(errorHandler) { deleteAllSuspend(threshold) }
    }

}