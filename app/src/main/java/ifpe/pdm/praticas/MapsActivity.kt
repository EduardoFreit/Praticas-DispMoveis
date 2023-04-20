package ifpe.pdm.praticas

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

    private var fine_location: Boolean = false
    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    companion object {
        val FINE_LOCATION_REQUEST : Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestPermission();

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun requestPermission() {
        val permissionCheck: Int = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
        this.fine_location = permissionCheck == PackageManager.PERMISSION_GRANTED
        if (this.fine_location) return
        ActivityCompat.requestPermissions(
            this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
            FINE_LOCATION_REQUEST
        )
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val granted: Boolean = grantResults.isNotEmpty() && (grantResults[0] == PackageManager.PERMISSION_GRANTED)
        this.fine_location = (requestCode == FINE_LOCATION_REQUEST) && granted
        mMap.isMyLocationEnabled = this.fine_location
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

        mMap.setOnMyLocationButtonClickListener {
            Toast.makeText(
                this@MapsActivity,
                "Indo para a sua localização.", Toast.LENGTH_SHORT
            ).show()
            false
        }

        mMap.setOnMyLocationClickListener { location: Location? ->
            Toast.makeText(
                this@MapsActivity,
                "Você está aqui!", Toast.LENGTH_SHORT
            ).show()
        }

        //Habilitando botão da minha localização se a permissão estiver sido garantida
        mMap.isMyLocationEnabled = this.fine_location
    }
}