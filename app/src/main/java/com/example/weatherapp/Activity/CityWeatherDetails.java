package com.example.weatherapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weatherapp.ModelClass.Root;
import com.example.weatherapp.R;
import com.example.weatherapp.Retrofit.Methods;
import com.example.weatherapp.Retrofit.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CityWeatherDetails extends AppCompatActivity {

    TextView cityName,cityTemp,cityCondition,cityTime;
    TextView cityHumidity,cityState,Latitude,Longitude,WindPressure,Fahrenheit;
    ImageView weatherIcon,backBtn;
    LinearLayout textViewLayout;
    ProgressDialog progressDialog;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_weather_details);

        cityName = findViewById(R.id.TVCityName);
        cityTemp = findViewById(R.id.TVCityDegree);
        cityCondition = findViewById(R.id.TVCityCondition);
        cityTime = findViewById(R.id.TVCityTime);
        cityHumidity = findViewById(R.id.Humidity);
        cityState = findViewById(R.id.state);
        Latitude = findViewById(R.id.latitude);
        Longitude = findViewById(R.id.longitude);
        WindPressure = findViewById(R.id.wind);
        Fahrenheit = findViewById(R.id.fahrenheit);
        textViewLayout = findViewById(R.id.TVLayout);
        weatherIcon = findViewById(R.id.weatherIcon);
        backBtn = findViewById(R.id.backBtn);

        context = this;

        progressDialog = new ProgressDialog(CityWeatherDetails.this);
        ((Activity) CityWeatherDetails.this).getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Loading...");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        Intent intent = getIntent();
        String latLon =intent.getStringExtra("latlon");

        Methods methods = RetrofitClient.getRetrofitInstance().create(Methods.class);
        Call<Root> call =methods.getAllData(latLon);

        call.enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {

                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                    ((Activity) CityWeatherDetails.this).getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                    textViewLayout.setVisibility(View.VISIBLE);
                }

                String icon = response.body().current.condition.icon;
                Log.e("TAG===========", "onResponse: "+ icon );
                Glide.with(CityWeatherDetails.this).load("https:"+icon).into(weatherIcon);



                String name = response.body().location.name;
                cityName.setText(name);

                double degree = response.body().current.temp_c;
                cityTemp.setText(degree+"°");

                String Condition = response.body().current.condition.text;
                cityCondition.setText(Condition);

                String Time = intent.getStringExtra("citytime");
                cityTime.setText(Time);

                double humidity = response.body().current.humidity;
                cityHumidity.setText(humidity+"");

                String state = response.body().location.region;
                cityState.setText(state);

                double lon = response.body().location.lon;
                Latitude.setText(lon+"");

                double lat = response.body().location.lat;
                Longitude.setText(lat+"");

                double far = response.body().current.temp_f;
                Fahrenheit.setText(far+"");

                double WindP = response.body().current.wind_kph;
                WindPressure.setText(WindP+"");
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Log.e("TAG","Error :- "+t.getMessage().toString());

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

}