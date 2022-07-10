package be.msdc.hypco.internal.utils

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.util.LongSparseArray
import androidx.core.app.NotificationCompat
import be.msdc.hypco.api.HypCo
import be.msdc.hypco.api.model.NodeItem
import be.msdc.hypco.internal.ui.activity.BaseHypCoActivity


class NotificationUtils(private val context: Context) {

    companion object    {
        private const val CHANNEL_ID = "hypco_lib"
        private const val ROOT_NOTIFICATION_ID = 1685
        private const val BUFFER_SIZE = 10

        private val itemBuffer: LongSparseArray<NodeItem> = LongSparseArray<NodeItem>(BUFFER_SIZE)
        private var itemCount: Int = 0

        @Synchronized
        fun clearBuffer() {
            itemBuffer.clear()
            itemCount = 0
        }

        @Synchronized
        fun addToBuffer(item: NodeItem) {
            itemCount++
            itemBuffer.put(item.id.hashCode().toLong(), item)
            if (itemBuffer.size() > BUFFER_SIZE) {
                itemBuffer.removeAt(0)
            }
        }
    }

    private val notificationManager: NotificationManager by lazy {
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    init {
        clearBuffer()
        notificationManager.createNotificationChannel(
            NotificationChannel(
                CHANNEL_ID,
                context.getString(be.msdc.hypco.R.string.hypco_notification),
                NotificationManager.IMPORTANCE_LOW
            )
        )
    }

    @Synchronized
    fun show(vararg item: NodeItem)  {
        item.forEach { addToBuffer(it) }
        if (!BaseHypCoActivity.isInForeground)  {
            val builder = NotificationCompat.Builder(context, CHANNEL_ID)
                .setContentIntent(PendingIntent.getActivity(context, 0, HypCo.getLaunchIntent(context), 0))
                .setLocalOnly(true)
                .setSmallIcon(be.msdc.hypco.R.drawable.ic_notification)
                .setColor(context.getColor(be.msdc.hypco.R.color.hypco_notification))
                .setContentTitle(context.getString(be.msdc.hypco.R.string.hypco_notification))
            val inbox = NotificationCompat.InboxStyle()
            for ((count, i) in (itemBuffer.size() - 1 downTo 0).withIndex()) {
                if (count < BUFFER_SIZE)    {
                    if (count == 0) {
                        builder.setContentText(itemBuffer.valueAt(i).getNotificationText())
                    }
                    inbox.addLine(itemBuffer.valueAt(i).getNotificationText())
                }
            }
            builder.setAutoCancel(false)
                .setStyle(inbox)
                .setSubText(itemCount.toString())
            notificationManager.notify(ROOT_NOTIFICATION_ID, builder.build())
        }
    }
}