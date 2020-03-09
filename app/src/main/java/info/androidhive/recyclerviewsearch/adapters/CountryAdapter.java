package info.androidhive.recyclerviewsearch.adapters;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;
import info.androidhive.recyclerviewsearch.R;
import info.androidhive.recyclerviewsearch.activites.SingleCountry;
import info.androidhive.recyclerviewsearch.model.Country;
import static android.content.ContentValues.TAG;

/**
 * Klasa u kojoj filtriram podatke
 * */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Country> countryList;
    private List<Country> countryListFiltered;


    public static final String EXTRA_FLAG = "flag";
    public static final String EXTRA_COUNTRY_NAME = "countryName";
    public static final String EXTRA_CAPITAL = "capital";
    public static final String EXTRA_CORDINATE = "latlong";
    public static final String EXTRA_REGION = "region";
    public static final String EXTRA_AREA = "area";
    public static final String EXTRA_POPULATION = "population";

    public interface CountriesAdapterListener {
        void onCountrySelected(Country country);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, capital;
        public ImageView thumbnail, full, empty;
        ConstraintLayout detail;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            capital = view.findViewById(R.id.capital);
            thumbnail = view.findViewById(R.id.thumbnail);
            detail=view.findViewById(R.id.detail);
            full=view.findViewById(R.id.full);
            empty=view.findViewById(R.id.empty);
        }
    }
    public CountryAdapter(Context context, List<Country> countryList, CountryAdapter.CountriesAdapterListener listener) {
        this.context = context;
        this.countryList = countryList;
        this.countryListFiltered = countryList;

    }


    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(itemView);
        return myViewHolder;
    }


    @Override
    public void onBindViewHolder(CountryAdapter.MyViewHolder holder, final int position) {
        final Country country = countryListFiltered.get(position);


        String url=country.getFlag();
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());



        Glide.with(context).load(country.getFlag()).into(holder.thumbnail); //Prikaz zastave unutar RecyclerView-a
        Glide.with(context).load(url).into(holder.thumbnail);


        holder.detail.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "onClick: clicked on: " + countryList.get(position));
                Intent intent = new Intent(context, SingleCountry.class);
                intent.putExtra(EXTRA_FLAG,country.getFlag()); //Prijenos linka od Flag, ovisno o odabranom gradu
                intent.putExtra(EXTRA_COUNTRY_NAME, country.getName());
                intent.putExtra(EXTRA_CAPITAL, country.getCapital());
                intent.putExtra(EXTRA_CORDINATE, country.getLatlng().toString()); //Dohvaćanje Kordinata
                intent.putExtra(EXTRA_POPULATION, country.getPopulation().toString());
                intent.putExtra(EXTRA_REGION,country.getRegion());
                intent.putExtra(EXTRA_AREA,country.getArea());
                context.startActivity(intent);
                Toast.makeText(context.getApplicationContext(), "Odabrali ste državu: "+country.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryListFiltered.size();
    }

    @Override
    public Filter getFilter()
    {
        return new Filter() {
            @Override
            //String pretraživanje prosljeđujem metodi performFiltering
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    countryListFiltered = countryList;
                } else {
                    List<Country> filteredList = new ArrayList<>();
                    for (Country row : countryList) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getCapital().contains(charSequence))
                        {
                            filteredList.add(row);
                        }
                    }
                    countryListFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = countryListFiltered;
                return filterResults;
            }
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                countryListFiltered = (ArrayList<Country>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}
