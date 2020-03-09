package info.androidhive.recyclerviewsearch.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import info.androidhive.recyclerviewsearch.model.FavoriteCountry;
@Dao
public interface FavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertFavoriteCountry(FavoriteCountry... favoriteCountry);

    @Delete
    void deleteFavorite(FavoriteCountry favoriteCountry);

    @Query("SELECT * FROM favorite_table ORDER BY id DESC" )
    LiveData<List<FavoriteCountry>> loadAllFavoriteCountry();
}
