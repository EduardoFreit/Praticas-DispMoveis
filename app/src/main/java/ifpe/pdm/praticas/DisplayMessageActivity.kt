package ifpe.pdm.praticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar

class DisplayMessageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_message)

        val intent : Intent = intent
        var message : String = intent.getStringExtra(MainActivity.EXTRA_MSG) ?: "Valor Nulo"
        val textView : TextView = findViewById(R.id.message_view)

        textView.textSize = 40F
        textView.text = message

        val ab : ActionBar? = supportActionBar
        ab?.setDisplayHomeAsUpEnabled(true)
    }
}