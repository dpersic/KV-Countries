package info.androidhive.recyclerviewsearch.activites;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Preconditions;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.io.File;

import info.androidhive.recyclerviewsearch.R;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CAPITAL;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CORDINATE;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_COUNTRY_NAME;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_FLAG;



public class SingleCountry extends AppCompatActivity {
    private static final String TAG = "SVGActivity";

    private RequestBuilder<PictureDrawable> requestBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_country);

        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_FLAG);
        String countryName = intent.getStringExtra(EXTRA_COUNTRY_NAME);
        String capitalCity=intent.getStringExtra(EXTRA_CAPITAL);
        String latlong= intent.getStringExtra(EXTRA_CORDINATE);


        ImageView imageView = findViewById(R.id.image_view_detail);

        TextView textViewCountryName = findViewById(R.id.text_view_country_name);
        TextView textViewcapitalCity= findViewById(R.id.text_view_capital_city);

        textViewCountryName.setText(latlong);
        textViewcapitalCity.setText(capitalCity);

        requestBuilder = Glide.with(this).as(PictureDrawable.class).transition(withCrossFade()).listener(new SvgSoftwareLayerSetter());
        Uri uri = Uri.parse(imageUrl);
        requestBuilder.load(uri).into(imageView);

    }
}

