package ifpe.pdm.praticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.Objects

class MainActivity : AppCompatActivity(), OverviewFragment.FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onInteraction(msg: String) {
        val fragment = supportFragmentManager.findFragmentById(R.id.detailFragment) as DetailFragment?
        if(Objects.nonNull(fragment) && fragment?.isVisible == true) {
            fragment.setText(msg)
        } else {
            val intent : Intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MSG, msg)
            startActivity(intent)
        }
    }
}