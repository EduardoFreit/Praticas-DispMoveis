package ifpe.pdm.praticas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import java.util.Objects

class MainActivity : AppCompatActivity(), OverviewFragment.FragmentListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val transact : FragmentTransaction = supportFragmentManager.beginTransaction()
        val viewDetail : View? = findViewById(R.id.detailFragment)
        val viewOverview : View? = findViewById(R.id.overviewFragment)
        if(viewDetail != null && viewDetail.isVisible) {
            transact.add(R.id.detailFragment, DetailFragment.newInstance(), "frag_detail")
        }
        if(viewOverview != null && viewOverview.isVisible) {
            transact.add(R.id.overviewFragment, OverviewFragment.newInstance(), "frag_overview")
        }
        transact.commit()
    }

    override fun onInteraction(msg: String) {
        val fragment = supportFragmentManager.findFragmentByTag("frag_detail") as DetailFragment?
        if(Objects.nonNull(fragment) && fragment?.isVisible == true) {
            fragment.setText(msg)
        } else {
            val intent = Intent(applicationContext, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_MSG, msg)
            startActivity(intent)
        }
    }
}