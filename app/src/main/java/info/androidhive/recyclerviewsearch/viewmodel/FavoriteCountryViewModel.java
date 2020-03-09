package info.androidhive.recyclerviewsearch.viewmodel;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.Database;

import java.util.List;

import info.androidhive.recyclerviewsearch.model.FavoriteCountry;


public class FavoriteCountryViewModel extends AndroidViewModel
{
    private FavoriteDAO favoriteDAO;
    private LiveData<List<FavoriteCountry>>liveData;

    public FavoriteCountryViewModel(@NonNull Application application)
    {
        super(application);
        favoriteDAO=DatabaseManager.getInstance(application).favoriteDAO();
        liveData=favoriteDAO.loadAllFavoriteCountry();
    }

    public LiveData<List<FavoriteCountry>>getFavorites(){
        return liveData;
    }

    public void insertFavorite(FavoriteCountry favoriteCountry)
    {
        new insertAnsync(favoriteDAO).execute(favoriteCountry);
    }

    public void deleteFavorite(FavoriteCountry favoriteCountry) // ako ne radi samo ovo zaljepi FavoriteCountry favoriteCountrya) //String Id
    {
        new deleteAnsync(favoriteDAO).execute(favoriteCountry);
    }

    private static class insertAnsync extends AsyncTask<FavoriteCountry,Void,Void> {
        private FavoriteDAO favoriteDAO;
        private insertAnsync(FavoriteDAO DAO) {
            favoriteDAO = DAO;
        }

        @Override
        protected Void doInBackground(FavoriteCountry... favoriteCountries) {
            favoriteDAO.insertFavoriteCountry(favoriteCountries[0]);
            return null;
        }
    }
    private static class deleteAnsync extends AsyncTask<FavoriteCountry,Void,Void>
    {
        FavoriteDAO favoriteDAO;

        private deleteAnsync(FavoriteDAO DAO)
        {
            favoriteDAO = DAO;
        }

        @Override
        protected Void doInBackground(FavoriteCountry... favoriteCountries) {
         //   Log.d("background", "doInBackground: " + favoriteCountries[0].getCountryName());
            favoriteDAO.deleteFavorite(favoriteCountries[0]);
            return null;
        }
    }
}

