package com.example.weatherapp.CustomAdapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.weatherapp.Interface.OnItemClickListener;
import com.example.weatherapp.ModelClass.CitiesStatic;
import com.example.weatherapp.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    ArrayList<CitiesStatic> cityArrayList;
    String cityTime;
    Context context;
    OnItemClickListener onItemClickListener;

    public CustomAdapter(ArrayList<CitiesStatic> cityArrayList, String cityTime, Context context, OnItemClickListener onItemClickListener) {
        this.cityArrayList = cityArrayList;
        this.cityTime = cityTime;
        this.context = context;
        this.onItemClickListener = onItemClickListener;

        Log.e("TAG", "CustomAdapter: "+ cityArrayList.toString() );
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Log.e("TAG", "onBindViewHolder: "+cityArrayList.get(position).getCityName().toString());

        holder.CityName.setText(cityArrayList.get(position).getCityName());
        holder.CityTime.setText(cityArrayList.get(position).getCityTime());

    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView CityName, CityTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            CityName = itemView.findViewById(R.id.tv_city);
            CityTime = itemView.findViewById(R.id.tv_time);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onClick(view,getAdapterPosition());
                }
            });
        }

    }
    public void filterList(ArrayList<CitiesStatic> filterdNames) {
        this.cityArrayList = filterdNames;
        notifyDataSetChanged();
    }
}