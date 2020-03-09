package info.androidhive.recyclerviewsearch.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerviewsearch.R;
import info.androidhive.recyclerviewsearch.adapters.CountryAdapter;
import info.androidhive.recyclerviewsearch.adapters.FavoritesAdapter;
import info.androidhive.recyclerviewsearch.model.FavoriteCountry;
import info.androidhive.recyclerviewsearch.viewmodel.FavoriteCountryViewModel;
/*     Activity za prikaz svih država koje su dodane u favorite    */

public class FavoriteActivity extends AppCompatActivity {

    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);
        //Veza sa ViewModel-om
        final FavoriteCountryViewModel viewModel= ViewModelProviders.of(this).get(FavoriteCountryViewModel.class);

        adapter=new FavoritesAdapter();
        RecyclerView recyclerView=findViewById(R.id.recycler_view_favorites);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel.getFavorites().observe(this, new Observer<List<FavoriteCountry>>()
        {
            @Override
            public void onChanged(List<FavoriteCountry> favoriteCountries)
            {
                adapter.SetList(favoriteCountries);
            }
        });
        adapter.setOnDeleteClickListener(new FavoritesAdapter.OnDeleteClickListener() {
            @Override
            public void OnItemClick(FavoriteCountry favoriteCountry) {
                viewModel.deleteFavorite(favoriteCountry);
                Toast.makeText(viewModel.getApplication(),"Uspiješno ste obrisali državu iz favorita: "+favoriteCountry.getCountryName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
