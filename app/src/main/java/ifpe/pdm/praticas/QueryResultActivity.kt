package ifpe.pdm.praticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import ifpe.pdm.praticas.databinding.ActivityQueryResultBinding

class QueryResultActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityQueryResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityQueryResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        val layout = viewBinding.queryResult
        val intent = getIntent()
        val data = intent.getCharSequenceArrayListExtra("data")
        if (data != null) {
            for (entry in data) {
                val text = TextView(this)
                text.text = entry
                text.layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                layout.addView(text)
            }
        }

    }
}