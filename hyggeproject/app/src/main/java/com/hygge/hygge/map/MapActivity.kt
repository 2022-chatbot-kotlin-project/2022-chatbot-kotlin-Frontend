package kbSoftware.hygge.map

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Transformations.map

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.hygge.hygge.R
import com.hygge.hygge.databinding.ActivityMapsBinding
import com.hygge.hygge.map.utillity.RetrofitUtil
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.coroutineContext
import com.hygge.hygge.map.shelter.Shelter

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, CoroutineScope {

    private lateinit var map: GoogleMap
    private lateinit var locationManager: LocationManager
    private lateinit var binding: ActivityMapsBinding

    private lateinit var myLocationListener: MyLocationListener

    companion object {
        const val PERMISSION_REQUEST_CODE = 101
        const val CAMERA_ZOOM_LEVEL = 17f
    }

    private var currentSelectMarker: Marker? = null

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        job = Job()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        bindViews()

    }

    private final fun bindViews() = with(binding) {
        //currentLocationButton.setImageDrawable(R.drawable.ic_current_location_button)

        currentLocationButton.setOnClickListener {
            Log.d("*****", "현재 위치 버튼 눌림")
            getMyLocation()
        }
    }

    private fun setupShelterMarker() {
        val shelterArray = Array<Shelter?>(2){null}
        shelterArray[0] = Shelter(1, "서울시립용산일시청소년쉼터", "서울 용산구 만리재로 156-1", "02-718-1318", "",
            "공용", "일시", 10, "http://www.nuryworld.kr/", 37.5528471041831, 126.964368040702)

        val shelterMarkerOptions : MarkerOptions = MarkerOptions()
        shelterMarkerOptions.apply {
            position(LatLng(shelterArray[0]!!.latitude, shelterArray[0]!!.longitude))
            title(shelterArray[0]!!.name)
        }
        map.addMarker(shelterMarkerOptions)
        Log.d("*****", "shelterMarkerOptions 추가 완료")
    }

    private fun setupMarker(searchResult: SearchResultEntity): Marker {
        currentSelectMarker?.remove()

        val positionLatLng = LatLng(
            searchResult.locationLatLng.latitude.toDouble(),
            searchResult.locationLatLng.longitude.toDouble()
        )
        val markerOption = MarkerOptions().apply {
            position(positionLatLng)
            title(searchResult.name)
            snippet(searchResult.fullAdress)
        }
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(positionLatLng, CAMERA_ZOOM_LEVEL))

        return map.addMarker(markerOption)!!
    }



    private fun getMyLocation() {
        if (::locationManager.isInitialized.not()) {
            locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        }

        val isGpsEnable = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        if (isGpsEnable) {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ),
                    PERMISSION_REQUEST_CODE
                )
            } else {
                setMyLocationListener()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun setMyLocationListener() {
        val minTime: Long = 1500
        val minDistance = 100f
        if (::myLocationListener.isInitialized.not()) {
            myLocationListener = MyLocationListener()
        }
        with(locationManager) {
            requestLocationUpdates(
                LocationManager.GPS_PROVIDER,
                minTime, minDistance, myLocationListener
            )
            requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                minTime, minDistance, myLocationListener
            )
        }
    }

    private fun onCurrentLocationChanged(locationEntity: LocationLatLngEntity) {
        map.moveCamera(
            CameraUpdateFactory.newLatLngZoom(
                LatLng(
                    locationEntity.latitude.toDouble(),
                    locationEntity.longitude.toDouble()
                ),
                CAMERA_ZOOM_LEVEL
            )
        )
        loadReverseGeoInformation(locationEntity)
        removeLocationListener()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
            ) {
                setMyLocationListener()
            } else {
                Toast.makeText(this, "권한을 받지 못했습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadReverseGeoInformation(locationEntity: LocationLatLngEntity) {
        launch(coroutineContext) {
            try {
                withContext(Dispatchers.IO) {
                    val response = RetrofitUtil.apiService.getReverseGeoCode(
                        lat = locationEntity.latitude.toDouble(),
                        lon = locationEntity.longitude.toDouble()
                    )



                    if (response.isSuccessful) {
                        val body = response.body()
                        withContext(Dispatchers.Main) {
                            Log.e("list", body.toString())
                            body?.let {
                                currentSelectMarker = setupMarker(
                                    SearchResultEntity(
                                        fullAdress = it.addressInfo.fullAddress ?: "",
                                        name = "내 위치",
                                        locationLatLng = locationEntity
                                    )
                                )
                                currentSelectMarker?.showInfoWindow()
                            }
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(this@MapsActivity, "검색하는 과정에서 에러가 발생했습니다. : ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun removeLocationListener() {
        if (::locationManager.isInitialized && ::myLocationListener.isInitialized) {
            locationManager.removeUpdates(myLocationListener)
        }
    }

    inner class MyLocationListener : LocationListener {

        override fun onLocationChanged(location: Location) {
            val locationLatLngEntity = LocationLatLngEntity(
                location.latitude.toFloat(),
                location.longitude.toFloat()
            )
            onCurrentLocationChanged(locationLatLngEntity)
        }

    }

    data class SearchResultEntity(
        val fullAdress: String,
        val name: String,
        val locationLatLng: LocationLatLngEntity
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readString()!!,
            parcel.readString()!!,
            parcel.readParcelable(LocationLatLngEntity::class.java.classLoader)!!
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeString(fullAdress)
            parcel.writeString(name)
            parcel.writeParcelable(locationLatLng, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<SearchResultEntity> {
            override fun createFromParcel(parcel: Parcel): SearchResultEntity {
                return SearchResultEntity(parcel)
            }

            override fun newArray(size: Int): Array<SearchResultEntity?> {
                return arrayOfNulls(size)
            }
        }
    }

    data class LocationLatLngEntity(
        val latitude: Float,
        val longitude: Float
    ): Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readFloat(),
            parcel.readFloat()
        ) {
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeFloat(latitude)
            parcel.writeFloat(longitude)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<LocationLatLngEntity> {
            override fun createFromParcel(parcel: Parcel): LocationLatLngEntity {
                return LocationLatLngEntity(parcel)
            }

            override fun newArray(size: Int): Array<LocationLatLngEntity?> {

                return arrayOfNulls(size)
            }
        }
    }



    /* Manipulates the map once available.
    * This callback is triggered when the map is ready to be used.
    * This is where we can add markers or lines, add listeners or move the camera. In this case,
    * we just add a marker near Sydney, Australia.
    * If Google Play services is not installed on the device, the user will be prompted to install
    * it inside the SupportMapFragment. This method will only be triggered once the user has
    * installed Google Play services and returned to the app.
    */

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Add a marker in Sydney and move the camera
/*      val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney))*/

        getMyLocation()
        setupShelterMarker()

/*        val sydney = LatLng(-34.0, 151.0)
        map.addMarker(MarkerOptions().position(sydney))*/


    }



}

private fun FloatingActionButton.setImageDrawable(icCurrentLocationButton: Int) {

}


