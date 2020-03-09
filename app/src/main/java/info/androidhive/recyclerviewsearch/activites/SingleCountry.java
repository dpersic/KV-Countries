package info.androidhive.recyclerviewsearch.activites;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.util.Preconditions;
import androidx.lifecycle.ViewModelProviders;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.google.android.gms.maps.MapView;

import org.w3c.dom.Text;

import java.io.File;

import info.androidhive.recyclerviewsearch.R;
import info.androidhive.recyclerviewsearch.model.FavoriteCountry;
import info.androidhive.recyclerviewsearch.viewmodel.FavoriteCountryViewModel;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_AREA;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CAPITAL;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_CORDINATE;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_COUNTRY_NAME;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_FLAG;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_POPULATION;
import static info.androidhive.recyclerviewsearch.adapters.CountryAdapter.EXTRA_REGION;


public class SingleCountry extends AppCompatActivity {
   //private static final String TAG = "SVGActivity";
    private RequestBuilder<PictureDrawable> requestBuilder;
    private Button btnMaps;
    private ImageButton btnFavorites;
    private FavoriteCountryViewModel viewModel;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_country);

        final Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_FLAG);
        final String countryName = intent.getStringExtra(EXTRA_COUNTRY_NAME);
        final String capitalCity=intent.getStringExtra(EXTRA_CAPITAL);
        final String population=intent.getStringExtra(EXTRA_POPULATION);
        final String area=intent.getStringExtra(EXTRA_AREA);
        final String region=intent.getStringExtra(EXTRA_REGION);


        ImageView imageView = findViewById(R.id.image_view_detail);
        TextView textViewCountryName = findViewById(R.id.text_view_country_name);
        TextView textViewcapitalCity= findViewById(R.id.text_view_capital_city);
        TextView textViewPopulation=findViewById(R.id.text_view_popilation);
        TextView textViewRegion=findViewById(R.id.text_view_region);
        TextView textViewArea=findViewById(R.id.text_view_area);
        //Veza sa ViewModela
        viewModel= ViewModelProviders.of(this).get(FavoriteCountryViewModel.class);
        final FavoriteCountry favorite=new FavoriteCountry();
        btnMaps=findViewById(R.id.button_GoogleMaps);
                btnMaps.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        Intent i = new Intent (v.getContext(), MapSingleCountry.class);
                        String latlong= intent.getStringExtra(EXTRA_CORDINATE);
                        Toast.makeText(v.getContext(),"Pričekajte trenutak, kliknuli ste na državu: "+countryName,Toast.LENGTH_LONG).show();
                        i.putExtra("kordinate",latlong);
                        startActivity(i);
                    }
                });
                btnFavorites=findViewById(R.id.imgButtonFavorites);
                btnFavorites.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        favorite.setCountryName(countryName);    //Spremanja naziva države u bazu
                        //favorite.setCapitalCity(capitalCity); // Spremanje grada u bazu
                        viewModel.insertFavorite(favorite);
                        Toast.makeText(v.getContext(),"Uspiješno ste dodali u favorite: "+favorite.getCountryName(),Toast.LENGTH_LONG).show();
                    }
                });
        textViewCountryName.setText(countryName);
        textViewcapitalCity.setText(capitalCity);
        textViewPopulation.setText(population);
        textViewRegion.setText(region);
        textViewArea.setText(area + " km2");
        requestBuilder = Glide.with(this).as(PictureDrawable.class).transition(withCrossFade()).listener(new SvgSoftwareLayerSetter());
        Uri uri = Uri.parse(imageUrl);
        requestBuilder.load(uri).into(imageView);
    }
}

