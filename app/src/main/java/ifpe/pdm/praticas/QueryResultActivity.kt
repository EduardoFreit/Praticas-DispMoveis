package ifpe.pdm.praticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ifpe.pdm.praticas.databinding.ActivityQueryResultBinding

class QueryResultActivity : AppCompatActivity() {
    private lateinit var viewBinding: ActivityQueryResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityQueryResultBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}