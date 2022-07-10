package be.msdc.hypco.api

import android.content.Context
import android.content.Intent
import be.msdc.hypco.api.reporter.AdvancedHypCo
import be.msdc.hypco.api.reporter.AdvancedSuspendHypCo
import be.msdc.hypco.api.reporter.SimpleHypCo
import be.msdc.hypco.api.reporter.SimpleSuspendHypCo
import be.msdc.hypco.internal.data.repository.RepositoryProvider
import be.msdc.hypco.internal.ui.activity.MainActivity
import be.msdc.hypco.internal.utils.NotificationUtils

class HypCo internal constructor() {

    companion object {
        internal var showNotification = true
        internal var notificationUtils: NotificationUtils? = null

        fun initialize(applicationContext: Context, showNotification: Boolean = true) {
            HypCo.showNotification = showNotification
            RepositoryProvider.initialize(applicationContext)
            notificationUtils = NotificationUtils(applicationContext)
        }

        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }

        val builder = ItemBuilder.instance

        val simple: SimpleHypCo = SimpleHypCo()
        val advanced: AdvancedHypCo = AdvancedHypCo()

        val simpleSuspend: SimpleSuspendHypCo = SimpleSuspendHypCo()
        val advancedSuspend: AdvancedSuspendHypCo = AdvancedSuspendHypCo()
    }
}

@Target(AnnotationTarget.FUNCTION)
annotation class HypCoRemoveInRelease