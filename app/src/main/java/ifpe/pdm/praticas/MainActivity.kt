package ifpe.pdm.praticas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity(), OverviewFragment.FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onInteraction(msg: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.detailFragment) as DetailFragment?
        fragment?.let {
            if(it.isVisible) fragment.setText(msg)
        }
    }
}