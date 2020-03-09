package info.androidhive.recyclerviewsearch.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import info.androidhive.recyclerviewsearch.model.FavoriteCountry;

@Database(version = 1,entities = FavoriteCountry.class)


public abstract class DatabaseManager extends RoomDatabase {

private static DatabaseManager instance;
public abstract FavoriteDAO favoriteDAO();


public static synchronized DatabaseManager getInstance(Context context)
{
    if(instance==null)
    {
        instance= Room.databaseBuilder(context.getApplicationContext(),DatabaseManager.class,"favorite_table").fallbackToDestructiveMigration().allowMainThreadQueries().build();
    }
    return  instance;
}

}
