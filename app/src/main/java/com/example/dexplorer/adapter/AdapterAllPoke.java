package com.example.dexplorer.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dexplorer.R;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.Sprites;

import java.util.List;

public class AdapterAllPoke extends RecyclerView.Adapter<AdapterAllPoke.MyViewHolder>{
    private List<Pokemom> listaPoke;
    public AdapterAllPoke(List<Pokemom> lista) {
//        for(Pokemom pokemom: lista){
//            Sprites sprite  = pokemom.getSprites();
//
//
//        }
        this.listaPoke = lista;
    }

    @NonNull
    @Override
    public AdapterAllPoke.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_listview, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAllPoke.MyViewHolder holder, int position) {
        Pokemom pokemon = listaPoke.get(position);

        holder.nome.setText(pokemon.getName());
        holder.id_pokemon.setText("#"+String.valueOf(pokemon.getId()));
        Glide.with(holder.itemView.getContext()).load(pokemon.getSprites().getFrontDefault()).into(holder.img_pokemon);
        AdapterTypeItem adaptertype = new AdapterTypeItem(pokemon.getTypes());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);

        holder.rv_types.setAdapter(adaptertype);
        holder.rv_types.setLayoutManager(layoutManager);
    }

    @Override
    public int getItemCount() {
        return listaPoke.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome;
        TextView id_pokemon;
        ImageView img_pokemon;
        RecyclerView rv_types;

//        TextView type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tv_NomePokemom);
            id_pokemon = itemView.findViewById((R.id.tv_IdPokemom));
            img_pokemon = itemView.findViewById(R.id.iv_ImgPokemon);
            rv_types = itemView.findViewById(R.id.rv_type);
//            type = itemView.findViewById(R.id.tv_Type);

        }
    }
}
