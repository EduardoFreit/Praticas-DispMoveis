package ifpe.pdm.praticas

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import ifpe.pdm.praticas.databinding.ActivityForecastBinding


class ForecastActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityForecastBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityForecastBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val layout = viewBinding.layoutForecast
        val data = intent.getCharSequenceArrayListExtra("data")
        for (entry in data!!) {
            val text = TextView(this)
            text.text = entry
            text.textSize = 20f
            text.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layout.addView(text)
        }
    }
}