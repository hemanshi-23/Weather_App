package com.example.weatherapp.ModelClass;

public class CitiesStatic {

    String cityName;
    String cityTime;
    String LatLong;

    public String getLatLong() {
        return LatLong;
    }

    public void setLatLong(String latLong) {
        LatLong = latLong;
    }

    public CitiesStatic(String cityName, String cityTime,String LatLong) {
        this.cityName = cityName;
        this.cityTime = cityTime;
        this.LatLong = LatLong;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityTime() {
        return cityTime;
    }

    public void setCityTime(String cityTime) {
        this.cityTime = cityTime;
    }

    @Override
    public String toString() {
        return "CitiesStatic{" +
                "cityName='" + cityName + '\'' +
                ", cityTime='" + cityTime + '\'' +
                '}';
    }
}
