package ifpe.pdm.praticas

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import ifpe.pdm.praticas.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        viewBinding.buttonAgendar.setOnClickListener { scheduleAlarm() }
    }

    private fun scheduleAlarm() {
        var time: Long = System.currentTimeMillis() + 10 * 1000

        val tempoInput: String = viewBinding.inputText.text.toString()

        if (tempoInput != null && tempoInput.isNotBlank()) {
            time = System.currentTimeMillis() + (tempoInput.toLong()* 1000)
        }

        val intentAlarm = Intent(this, AlarmReceiver::class.java)

        val pendingAlarmIntent = PendingIntent.getBroadcast(
            this, 1, intentAlarm, PendingIntent.FLAG_IMMUTABLE
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        alarmManager.set(AlarmManager.RTC_WAKEUP, time, pendingAlarmIntent)

        Toast.makeText(this, "Alarme agendado.", Toast.LENGTH_LONG).show()

        finish()
    }
}