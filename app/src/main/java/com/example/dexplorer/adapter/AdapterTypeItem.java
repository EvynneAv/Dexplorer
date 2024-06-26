package com.example.dexplorer.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexplorer.R;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonType;
import com.example.dexplorer.model.PokemonTypeDetail;

import java.util.ArrayList;
import java.util.List;

public class AdapterTypeItem extends RecyclerView.Adapter<AdapterTypeItem.MyViewHolder>{
    private List<PokemonType> type;


    public AdapterTypeItem(List<PokemonType> listTypes){

        this.type = listTypes;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_type_listview,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.typeName.setText(type.get(position).getType().getName());

    }


    @Override
    public int getItemCount() {
        return type.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView typeName;

        public MyViewHolder(View itemView) {
            super(itemView);
            typeName = itemView.findViewById(R.id.tv_type_name);
        }
    }
}
