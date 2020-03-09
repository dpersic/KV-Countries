package info.androidhive.recyclerviewsearch.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.recyclerviewsearch.R;
import info.androidhive.recyclerviewsearch.activites.FavoriteActivity;
import info.androidhive.recyclerviewsearch.model.FavoriteCountry;
import info.androidhive.recyclerviewsearch.viewmodel.FavoriteCountryViewModel;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder> {

   //private FavoriteCountryViewModel viewModelDelete;
   //private List<FavoriteCountry> favoriteCountries;
    private List<FavoriteCountry> favoriteCountries = new ArrayList<>();
    private OnDeleteClickListener onDeleteClickListener;

  /* public FavoritesAdapter(List<FavoriteCountry> favorites, FavoriteCountryViewModel viewModel)
    {
        this.viewModelDelete=viewModel;
        favoriteCountries=favorites;

    }*/

    public void SetList(List<FavoriteCountry> list){
        this.favoriteCountries = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);


        // Inflate the custom layout
        View favoriteView=inflater.inflate(R.layout.item_favorites,parent,false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(favoriteView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final FavoritesAdapter.ViewHolder viewHolder, final int position) {

        // Get the data model based on position
         FavoriteCountry favoritecountry= favoriteCountries.get(position);


        // Set item views based on your views and data model
        TextView textView = viewHolder.nameTextView;
        textView.setText(favoritecountry.getCountryName()); //ISPIS IMENA DRZAVE

        ImageButton imagebuttonDelete = viewHolder.imgbuttonDelete;
        imagebuttonDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Log.d("ID", "onClick: "+favoriteCountries.get(position).getId());
                //viewModelDelete.deleteFavorite();

                if(onDeleteClickListener != null) {
                    onDeleteClickListener.OnItemClick(favoriteCountries.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView nameTextView;
    public ImageButton imgbuttonDelete;

    public ViewHolder(View itemView){
        super(itemView);

        nameTextView=itemView.findViewById(R.id.country_name);
        imgbuttonDelete=itemView.findViewById(R.id.delete_button);
    }
}
    public interface OnDeleteClickListener{
        void OnItemClick(FavoriteCountry favoriteCountry);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener){
        this.onDeleteClickListener = listener;
    }
}
