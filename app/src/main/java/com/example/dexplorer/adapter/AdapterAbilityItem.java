package com.example.dexplorer.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexplorer.R;
import com.example.dexplorer.model.AbilitySlot;

import java.util.List;

public class AdapterAbilityItem extends RecyclerView.Adapter<AdapterAbilityItem.MyViewHolder>{
    private List<AbilitySlot> abilitieSlots;

    public AdapterAbilityItem(List<AbilitySlot> abilities){
        this.abilitieSlots = abilities;
    }
    @NonNull
    @Override
    public AdapterAbilityItem.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_ability,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AbilitySlot aux = abilitieSlots.get(position);
        holder.nameAbility.setText(aux.getAbility().getName());
    }


    @Override
    public int getItemCount() {
        return abilitieSlots.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView nameAbility;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameAbility = itemView.findViewById(R.id.tv_ability_item);
        }
    }
}
