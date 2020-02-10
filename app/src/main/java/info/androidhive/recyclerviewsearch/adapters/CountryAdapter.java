package info.androidhive.recyclerviewsearch.adapters;

import android.content.Context;
import android.content.Intent;
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

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerviewsearch.R;
import info.androidhive.recyclerviewsearch.activites.SingleCountry;
import info.androidhive.recyclerviewsearch.model.Country;

import static android.content.ContentValues.TAG;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> implements Filterable {

    private Context context;
    private List<Country> countryList;
    private List<Country> countryListFiltered;
   // private CountriesAdapterListener listener;

    public static final String EXTRA_FLAG = "flag";
    public static final String EXTRA_COUNTRY_NAME = "countryName";
    public static final String EXTRA_CAPITAL = "capital";
    public static final String EXTRA_CORDINATE = "latlong";
    public static final String EXTRA_REGION = "region";
    public static final String EXTRA_SUBREGION = "subregion";
    public static final String EXTRA_POPULATION = "population";

    public interface CountriesAdapterListener {
        void onContactSelected(Country country);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, capital,region, subregion;
        public ImageView thumbnail;


        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            capital = view.findViewById(R.id.capital);
            thumbnail = view.findViewById(R.id.thumbnail);

        }
    }
    public CountryAdapter(Context context, List<Country> countryList, CountryAdapter.CountriesAdapterListener listener) {
        this.context = context;
       // this.listener = listener;
        this.countryList = countryList;
        this.countryListFiltered = countryList;
    }


    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row_item, parent, false);

        return new CountryAdapter.MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(CountryAdapter.MyViewHolder holder, final int position) {
        final Country country = countryListFiltered.get(position);

        String url=country.getFlag();
        holder.name.setText(country.getName());
        holder.capital.setText(country.getCapital());


        Glide.with(context).load(country.getFlag()).into(holder.thumbnail); //Prikaz zastave unutar RecyclerView-a
        Glide.with(context).load(url).into(holder.thumbnail);


        holder.name.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: clicked on: " + countryList.get(position));

                Intent intent = new Intent(context, SingleCountry.class);
                intent.putExtra(EXTRA_FLAG,country.getFlag()); //Prijenos linka od Flag, ovisno o odabranom gradu
                intent.putExtra(EXTRA_COUNTRY_NAME, country.getName());
                intent.putExtra(EXTRA_CAPITAL, country.getCapital());
                intent.putExtra(EXTRA_CORDINATE, String.valueOf(country.getLatlng())); //Dohvaćanje Kordinata
                context.startActivity(intent);

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
