package com.app.quizoofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    Context context;
    List<CategoryData> categoryData;

    public  CategoryAdapter(List<CategoryData> categoryData, Context model){
        this.categoryData = categoryData;
        this.context=model;
    }

    public CategoryAdapter() {
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list,parent,false);
        ViewHolder viewHolder= new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        CategoryData categoryDatainfo= categoryData.get(position);
        holder.categoryname.setText(categoryDatainfo.getName());
        Glide.with(holder.categoryimage.getContext()).load(categoryDatainfo.getImage()).into(holder.categoryimage);
    }

    @Override
    public int getItemCount() {
        return categoryData.size()
                ;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryimage;
        TextView categoryname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryimage=itemView.findViewById(R.id.category_image);
            categoryname=itemView.findViewById(R.id.category_name);
        }
    }
}
