package ifpe.pdm.praticas

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import ifpe.pdm.praticas.databinding.ActivityMapsBinding
import java.util.Date


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val recife = LatLng(-8.05, -34.9)
        val caruaru = LatLng(-8.27, -35.98)
        val joaopessoa = LatLng(-7.12, -34.84)


        mMap.addMarker(
            MarkerOptions().position(recife).title("Recife")
                .icon(BitmapDescriptorFactory.defaultMarker(35f))
        )
        mMap.addMarker(
            MarkerOptions().position(caruaru).title("Caruaru")
                .icon(BitmapDescriptorFactory.defaultMarker(120f))
        )
        mMap.addMarker(
            MarkerOptions().position(joaopessoa).title("João Pessoa")
                .icon(BitmapDescriptorFactory.defaultMarker(230f))
        )

        //Mostrar um toast quando clica em algum marcador
        mMap.setOnMarkerClickListener { marker: Marker ->
            Toast.makeText(
                this@MapsActivity,
                "Você clicou em " + marker.title,
                Toast.LENGTH_SHORT
            ).show()
            false
        }

        //Adiciona um marcador ao clicar em algum lugar do mapa
        mMap.setOnMapClickListener { latLng: LatLng ->
            mMap.addMarker(
                MarkerOptions()
                    .position(latLng)
                    .title("Adicionado em " + Date())
                    .icon(BitmapDescriptorFactory.defaultMarker(0f))
            )
        }

        mMap.moveCamera(CameraUpdateFactory.newLatLng(recife))
    }
}