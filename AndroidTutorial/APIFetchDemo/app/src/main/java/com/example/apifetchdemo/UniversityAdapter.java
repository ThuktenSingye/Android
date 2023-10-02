package com.example.apifetchdemo;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {
    private  List<University> universityList;

    public UniversityAdapter(List<University> universityList) {
        this.universityList = universityList;
    }
    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.university_item, parent, false);
        return new UniversityViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        University university = universityList.get(position);
        // Bind data to the views within each item
        holder.idText.setText("Name : "+university.getID());
        holder.priceText.setText("Price : "+university.getPrice());
        // You can bind more data as needed
    }
    public void setUniversityList(List<University> universityList){
        this.universityList = universityList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return universityList.size();
    }
    public class UniversityViewHolder extends RecyclerView.ViewHolder {
        TextView idText;
        TextView priceText;
        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            idText = itemView.findViewById(R.id.id); // Replace with your TextView IDs
            priceText = itemView.findViewById(R.id.price);
        }
    }
}
