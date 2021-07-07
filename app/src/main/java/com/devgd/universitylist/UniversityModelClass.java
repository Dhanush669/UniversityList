package com.devgd.universitylist;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class UniversityModelClass {
    @PrimaryKey(autoGenerate = true)
    int id;
    String nameOfCollege;
    String countryOfCollege;
    String stateOfCollege;
    String urlOfCollege;

    public UniversityModelClass(String nameOfCollege, String countryOfCollege, String stateOfCollege, String urlOfCollege) {
        this.nameOfCollege = nameOfCollege;
        this.countryOfCollege = countryOfCollege;
        this.stateOfCollege = stateOfCollege;
        this.urlOfCollege = urlOfCollege;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameOfCollege() {
        return nameOfCollege;
    }

    public void setNameOfCollege(String nameOfCollege) {
        this.nameOfCollege = nameOfCollege;
    }

    public String getCountryOfCollege() {
        return countryOfCollege;
    }

    public void setCountryOfCollege(String countryOfCollege) {
        this.countryOfCollege = countryOfCollege;
    }

    public String getStateOfCollege() {
        return stateOfCollege;
    }

    public void setStateOfCollege(String stateOfCollege) {
        this.stateOfCollege = stateOfCollege;
    }

    public String getUrlOfCollege() {
        return urlOfCollege;
    }

    public void setUrlOfCollege(String urlOfCollege) {
        this.urlOfCollege = urlOfCollege;
    }
}
