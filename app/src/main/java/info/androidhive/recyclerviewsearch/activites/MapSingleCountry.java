package info.androidhive.recyclerviewsearch.activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import info.androidhive.recyclerviewsearch.R;

import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CORDINATE;

 /*    Activity za prikaz države na karti    */

public class MapSingleCountry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_single_country);

        Intent intent = getIntent();
        String latlong= intent.getStringExtra("kordinate");
        //Toast.makeText(this,latlong,Toast.LENGTH_LONG).show(); TEST
        String lat=latlong.substring(latlong.indexOf("[")+1,latlong.indexOf(","));
        String lng=latlong.substring(latlong.indexOf(",")+2,latlong.indexOf("]"));
        //Toast.makeText(this,lat+" "+lng,Toast.LENGTH_LONG).show();
        String URL="https://www.google.com/maps/@"+lat+","+lng+",5.0z";
        WebView map=findViewById(R.id.map);
        map.setWebViewClient(new WebViewClient());
        map.getSettings().setJavaScriptEnabled(true);
        map.loadUrl(URL);
    }
}
