package com.example.weatherapp.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.weatherapp.CustomAdapter.CustomAdapter;
import com.example.weatherapp.Interface.OnItemClickListener;
import com.example.weatherapp.ModelClass.CitiesStatic;
import com.example.weatherapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    RecyclerView recyclerView;
    ArrayList<CitiesStatic> cityArrayList ;
    CustomAdapter customAdapter;
    EditText searchEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        searchEditText = findViewById(R.id.Search_Edt_Txt);

        cityArrayList = new ArrayList<>();

        TimeZone tz = TimeZone.getTimeZone("GMT+05:30");
        Calendar c = Calendar.getInstance(tz);
        String time = String.format("%02d" , c.get(Calendar.HOUR_OF_DAY))+":"+
                String.format("%02d" , c.get(Calendar.MINUTE));

        cityArrayList.add(new CitiesStatic("Surat",time,"21.1702,72.8311"));
        cityArrayList.add(new CitiesStatic("Ahmedabad",time,"23.0225,72.5714"));
        cityArrayList.add(new CitiesStatic("Bombay",time,"19.0760,72.8777"));
        cityArrayList.add(new CitiesStatic("Pune",time,"18.5204,73.8567"));
        cityArrayList.add(new CitiesStatic("New Delhi",time,"28.6139,77.2090"));
        cityArrayList.add(new CitiesStatic("Gandhinagar",time,"23.2156,72.6369"));
        cityArrayList.add(new CitiesStatic("Calcutta",time,"22.5726,88.3639"));
        cityArrayList.add(new CitiesStatic("Chennai",time,"13.0827,80.2707"));
        cityArrayList.add(new CitiesStatic("Bangalore",time,"12.9716,77.5946"));
        cityArrayList.add(new CitiesStatic("Hyderabad",time,"17.3850,78.4867"));
        cityArrayList.add(new CitiesStatic("Jaipur",time,"26.9124,75.7873"));


        customAdapter = new CustomAdapter(cityArrayList,time,getApplicationContext(),this);
        recyclerView.setAdapter(customAdapter);

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());
            }
        });
    }
    @Override
    public void onClick(View view, int position) {

        Intent intent = new Intent(MainActivity.this, CityWeatherDetails.class);
        String LATLONG = cityArrayList.get(position).getLatLong();
        intent.putExtra("latlon",LATLONG);
        intent.putExtra("citytime",cityArrayList.get(position).getCityTime());
        startActivity(intent);
    }
    private void filter(String text) {

        ArrayList<CitiesStatic> filterdNames = new ArrayList<>();


        for (int i =0;i<cityArrayList.size();i++) {

            if (cityArrayList.get(i).getCityName().toLowerCase().contains(text.toLowerCase())) {

                filterdNames.add(cityArrayList.get(i));
            }
        }


        customAdapter.filterList(filterdNames);

    }

    @Override
    protected void onResume() {
        super.onResume();

        searchEditText.setText("");
    }
}