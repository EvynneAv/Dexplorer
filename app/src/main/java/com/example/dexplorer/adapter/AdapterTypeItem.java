package com.example.dexplorer.adapter;

import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexplorer.R;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonType;
import com.example.dexplorer.model.PokemonTypeDetail;
import com.example.dexplorer.utils.PokemonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
//        holder.typeName.setText(type.get(position).getType().getName());
//        String typeImageName = type.get(position).getType().getName()+".png";
//
//        int img = holder.itemView.getContext().getResources().getIdentifier(typeImageName, "drawable", holder.itemView.getContext().getPackageName());
//        holder.typeImage.setImageDrawable(holder.itemView.getContext().getDrawable(img));
        String typeName = type.get(position).getType().getName();
        String typeImageName = typeName.toLowerCase(); // Certifique-se de que o nome esteja em minúsculas para corresponder ao nome do arquivo drawable

        // Obtendo o ID do recurso drawable
        int imgResId = holder.itemView.getContext().getResources().getIdentifier(typeImageName, "drawable", holder.itemView.getContext().getPackageName());

        // Verificando se o recurso foi encontrado
        if (imgResId != 0) {
            // Definindo a imagem no ImageView
            holder.typeImage.setImageResource(imgResId);
        } else {
            // Se o recurso não foi encontrado, defina uma imagem padrão ou exiba uma mensagem de erro
            holder.typeImage.setImageResource(R.drawable.unknown); // Substitua com seu recurso padrão
        }
    }


    @Override
    public int getItemCount() {
        return type.size();
    }
    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        public TextView typeName;
        public ImageView typeImage;

        public MyViewHolder(View itemView) {
            super(itemView);
//            typeName = itemView.findViewById(R.id.tv_type_name);
            typeImage = itemView.findViewById(R.id.iv_typepokemon);
        }
    }
}
