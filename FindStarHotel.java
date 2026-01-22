package com.s22010176.finderapp;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class FindStarHotel extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private EditText locationText;
    private Button findButton;
    private String zoomLoc;
    List<Address> listGeoCoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.findstar_hotel);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        locationText = findViewById(R.id.findtxt2);
        findButton = findViewById(R.id.findbtn1);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        findButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (locationText != null) {
                    zoomLoc = locationText.getText().toString();
                    if (!zoomLoc.isEmpty()) {
                        try {
                            Geocoder geocoder = new Geocoder(FindStarHotel.this);
                            List<Address> addresses = geocoder.getFromLocationName(zoomLoc, 1);
                            if (addresses != null && !addresses.isEmpty()) {
                                Address address = addresses.get(0);
                                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12.0f));
                            } else {
                                Toast.makeText(FindStarHotel.this, "Location not found", Toast.LENGTH_SHORT).show();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    listGeoCoder = new Geocoder(FindStarHotel.this).getFromLocationName("kottawa", 1);
                    if (!listGeoCoder.isEmpty()) {
                        double longitude = listGeoCoder.get(0).getLongitude();
                        double latitude = listGeoCoder.get(0).getLatitude();
                        String countryName = listGeoCoder.get(0).getCountryName();
                        Log.i("GOOGLE_MAP_TAG", "Address has latitude : " + latitude + " longitude : " +
                                longitude + " Country name " + countryName);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;

        LatLng galadari = new LatLng(6.93193445340615, 79.84297335125524);
        mMap.addMarker(new MarkerOptions().position(galadari).title("Galadari"));

        LatLng kingsbury = new LatLng(6.933087201583308, 79.84198351078223);
        mMap.addMarker(new MarkerOptions().position(kingsbury).title("Kingsbury"));

        LatLng hilton = new LatLng(6.932820941896722, 79.84480519473787);
        mMap.addMarker(new MarkerOptions().position(hilton).title("Hilton"));

        LatLng cinnamon = new LatLng(6.929358422508174, 79.84928939514467);
        mMap.addMarker(new MarkerOptions().position(cinnamon).title("Cinnamon"));

        LatLng shangrila = new LatLng(6.9287315062239365, 79.84387629113718);
        mMap.addMarker(new MarkerOptions().position(shangrila).title("Shangri-La"));

        LatLng weligama = new LatLng(5.973253035155394, 80.43939476659173);
        mMap.addMarker(new MarkerOptions().position(weligama).title("Weligama Bay Marriott Resort"));

        LatLng marinobeach = new LatLng(6.90011596910566, 79.85160365496351);
        mMap.addMarker(new MarkerOptions().position(marinobeach).title("Marino Beach Hotel"));

        LatLng sofiacity = new LatLng(6.907897563153345, 79.85093496660029);
        mMap.addMarker(new MarkerOptions().position(sofiacity).title("Sofia Colombo City Hotel"));

        LatLng villathambaru= new LatLng(6.3916371608940015, 80.00643149543177);
        mMap.addMarker(new MarkerOptions().position(villathambaru).title("Villa Thambaru"));

        LatLng kolonna = new LatLng(6.958151439607137, 80.22645608009176);
        mMap.addMarker(new MarkerOptions().position(kolonna).title("Kolonna River Side Hotel"));

        LatLng seethawakaregency = new LatLng(6.944886494724386, 80.19886226474631);
        mMap.addMarker(new MarkerOptions().position(seethawakaregency).title("Seethawaka Regency"));

        LatLng kandula = new LatLng(6.961080538880968, 80.23842483771578);
        mMap.addMarker(new MarkerOptions().position(kandula).title("Hotel Kandula"));

        LatLng kashyapa = new LatLng(6.9208546570068945, 80.22282010892776);
        mMap.addMarker(new MarkerOptions().position(kashyapa).title("Hotel Kashyapa"));

        LatLng purplesun = new LatLng(6.948371743742939, 80.21186328009173);
        mMap.addMarker(new MarkerOptions().position(purplesun).title("Purple Sun"));

        









        mMap.getUiSettings().setZoomControlsEnabled(true);
    }
}
