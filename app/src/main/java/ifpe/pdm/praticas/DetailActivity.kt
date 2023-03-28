package ifpe.pdm.praticas

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(resources.configuration.orientation.equals(Configuration.ORIENTATION_LANDSCAPE)) {
            finish()
            return;
        }
        setContentView(R.layout.activity_detail)
    }

    companion object {
        const val EXTRA_MSG : String = "msg"
    }

    override fun onStart() {
        super.onStart()
        val extras : Bundle? = intent.extras
        extras?.let {
            val msg : String = it.getString(EXTRA_MSG) ?: throw java.lang.Exception("String não achada. key: $EXTRA_MSG")
            val detailFragment : DetailFragment = supportFragmentManager.findFragmentById(R.id.detailFragment) as DetailFragment
            detailFragment.setText(msg)
        }

    }
}