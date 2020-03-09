//package info.androidhive.recyclerviewsearch.activites;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentActivity;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.SupportMapFragment;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.MarkerOptions;
//
//import info.androidhive.recyclerviewsearch.R;
//
//import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_COUNTRY_NAME;
//import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CORDINATE;
//
//public class GoogleMapActivity extends AppCompatActivity implements OnMapReadyCallback {
//
//    private GoogleMap mMap;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_google_map);
//
//        SupportMapFragment mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
//
//
//
//
//        /*Intent intent = getIntent();
//        String str=intent.getExtras().getString("kordinate");
//        Toast.makeText(this, str+"BZVZ", Toast.LENGTH_SHORT).show();
//
//        TextView kordinate=findViewById(R.id.textView);
//        kordinate.setText(str);*/
//    }
//
//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//
//
//
//        //String countryName = intent.getStringExtra(EXTRA_COUNTRY_NAME); //Prikaz imena
//       //String latlong= intent.getStringExtra(EXTRA_CORDINATE);// Kordinate
//
//        // Add a marker in Sydney, Australia, and move the camera.
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
//
//    }
//}
