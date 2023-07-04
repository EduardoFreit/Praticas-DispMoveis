package ifpe.pdm.praticas

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if(context != null) {
            val newIntent = Intent(context, MainActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or
                    Intent.FLAG_ACTIVITY_CLEAR_TOP)
            val pendingNotificationIntent = PendingIntent.getActivity(
                context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val channelId = createChannel(context)

            val builder = NotificationCompat.Builder(context, channelId!!)
            builder.setSmallIcon(R.mipmap.ic_launcher)
            builder.setContentText("Alarme disparou! Toque para reagendar.")
            builder.setContentIntent(pendingNotificationIntent)
            builder.setAutoCancel(true)
            // Configurar vibração
            val pattern = longArrayOf(1000, 1000, 1000, 1000, 1000) // Padrão de vibração: 0ms de espera, 1000ms de vibração, 500ms de espera, 1000ms de vibração
            builder.setVibrate(pattern)

            val notification: Notification = builder.build()

            val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notification)
        }
    }

    private fun createChannel(context: Context): String? {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "Pratica11"
            val channel = NotificationChannel(
                channelId,
                "Canal Pratica11",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
            return channelId
        } else {
            return null
        }
    }
}