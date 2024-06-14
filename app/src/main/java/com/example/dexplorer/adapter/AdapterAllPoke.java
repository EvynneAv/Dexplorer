package com.example.dexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexplorer.R;
import com.example.dexplorer.model.Pokemom;

import java.util.List;

public class AdapterAllPoke extends RecyclerView.Adapter<AdapterAllPoke.MyViewHolder>{
    private List<Pokemom> listaPoke;
    public AdapterAllPoke(List<Pokemom> lista) {
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
    }

    @Override
    public int getItemCount() {
        return listaPoke.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.tv_NomePokemom);

        }
    }
}
