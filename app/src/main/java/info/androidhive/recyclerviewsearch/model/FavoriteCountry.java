package info.androidhive.recyclerviewsearch.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite_table")


public class FavoriteCountry {

    private String CountryName;
    private String CapitalCity;

   @PrimaryKey(autoGenerate = true)
    private int Id;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getCountryName() {
        return CountryName;
    }

    public void setCountryName(String countryName) {
        CountryName = countryName;
    }

    public String getCapitalCity() {
        return CapitalCity;
    }

    public void setCapitalCity(String capitalCity) {
        CapitalCity = capitalCity;
    }






}
