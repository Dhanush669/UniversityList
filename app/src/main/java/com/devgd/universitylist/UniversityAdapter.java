package com.devgd.universitylist;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.ViewHolder> {
    private List<UniversityModelClass> universityList =new ArrayList<>();
    Context context;
    int col;
    public UniversityAdapter(Context context) {
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view= LayoutInflater.from(parent.getContext()).inflate(R.layout.university_lists,parent,false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        UniversityModelClass modelClass=universityList.get(position);
        holder.clgName.setText(modelClass.getNameOfCollege());
        holder.clgLoc.setText(modelClass.getCountryOfCollege());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,UniversityDetails.class);

                intent.putExtra("country",modelClass.getCountryOfCollege());
                intent.putExtra("state",modelClass.getStateOfCollege());
                intent.putExtra("url",modelClass.getUrlOfCollege());
                intent.putExtra("name",modelClass.getNameOfCollege());

                context.startActivity(intent);
            }
        });
        col= new Random().nextInt(4);
        switch (col) {
            case 0:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.cardView.setCardBackgroundColor(context.getColor(R.color.color1));
                }
                break;
            case 1:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.cardView.setCardBackgroundColor(context.getColor(R.color.color2));
                }
                break;
            case 2:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.cardView.setCardBackgroundColor(context.getColor(R.color.color3));
                }
                break;
            default:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    holder.cardView.setCardBackgroundColor(context.getColor(R.color.colordef));
                }
        }
    }
    @Override
    public int getItemCount() {
        return universityList.size();
    }

    public void setList(List<UniversityModelClass> universityList){
        this.universityList=universityList;
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        TextView clgName,clgLoc;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            clgName=itemView.findViewById(R.id.clgName);
            clgLoc=itemView.findViewById(R.id.clgLoc);
            cardView=itemView.findViewById(R.id.cardView);
        }
    }

}
