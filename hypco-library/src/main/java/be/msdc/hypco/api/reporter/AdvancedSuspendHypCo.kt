package be.msdc.hypco.api.reporter

import java.util.*

open class AdvancedSuspendHypCo internal constructor() : IHypCoReporter() {

    suspend fun deleteAllSuspend() {
        repository.deleteAll()
    }

    suspend fun deleteAllSuspend(threshold: Date) {
        repository.deleteOld(threshold.time)
    }

}