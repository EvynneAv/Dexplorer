package com.example.dexplorer.activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dexplorer.R;
import com.example.dexplorer.adapter.AdapterAbilityItem;
import com.example.dexplorer.adapter.AdapterTypeItem;
import com.example.dexplorer.model.Pokemom;

public class PokemonInDetailsActivity extends AppCompatActivity {
    Pokemom selectedPokemon;
    ImageView pokemonPhoto;
    TextView pokemonName;
    TextView pokemonID;
    TextView pokemonHeight;
    TextView pokemonWeight;


    RecyclerView pokemonTypes;
    RecyclerView pokemonAbilities;

    TextView pokemonStatsHP;
    TextView pokemonStatsAttack;
    TextView pokemonStatsDefense;
    TextView pokemonStatsSPAttack;
    TextView pokemonStatsSPDefense;
    TextView pokemonStatsSpeed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pokemon_in_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        selectedPokemon = bundle.getParcelable("pokemon");

        pokemonName = findViewById(R.id.tv_PokemonNameInDetails);
        pokemonPhoto = findViewById(R.id.iv_PokemonPhotoInDetails);
        pokemonID = findViewById(R.id.tv_pokemonIDInDetails);
        pokemonTypes = findViewById(R.id.rv_typesInDetails);
        pokemonHeight = findViewById(R.id.tv_pokemonHeightInDetails);
        pokemonWeight = findViewById(R.id.tv_pokemonWeightInDetails);
        pokemonAbilities = findViewById(R.id.rv_abilityList);
        pokemonStatsHP = findViewById(R.id.tv_hpStatInDetails);
        pokemonStatsAttack = findViewById(R.id.tv_attackStatinDetails);
        pokemonStatsDefense = findViewById(R.id.tv_defenseStatInDetails);
        pokemonStatsDefense= findViewById(R.id.tv_defenseStatInDetails);
        pokemonStatsSPAttack = findViewById(R.id.tv_spAttackInDetails);
        pokemonStatsSPDefense = findViewById(R.id.tv_spDefenseInDetails);
        pokemonStatsSpeed = findViewById(R.id.tv_SpeedStatInDetails);



        Glide.with(getApplicationContext()).load(selectedPokemon.getSprites().getFrontDefault()).into(pokemonPhoto);
        pokemonName.setText(selectedPokemon.getName());
        pokemonID.setText(String.valueOf(selectedPokemon.getId()));
        AdapterTypeItem adaptertype = new AdapterTypeItem(selectedPokemon.getTypes());
        LinearLayoutManager layoutManagerTypeItems = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        pokemonTypes.setAdapter(adaptertype);
        pokemonTypes.setLayoutManager(layoutManagerTypeItems);
        pokemonWeight.setText(String.format("%.2f", selectedPokemon.getWeight() * 0.1)+" kg");
        pokemonHeight.setText(String.format("%.2f", selectedPokemon.getHeight() * 0.1) + " m");
        AdapterAbilityItem adapterAbility = new AdapterAbilityItem(selectedPokemon.getAbilities());
        pokemonAbilities.setAdapter(adapterAbility);
        LinearLayoutManager layoutManagerAbilityItems = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        pokemonAbilities.setLayoutManager(layoutManagerAbilityItems);


        pokemonStatsHP.setText((String.valueOf(selectedPokemon.getStats().get(0).getBase_stat())));
        pokemonStatsAttack.setText((String.valueOf(selectedPokemon.getStats().get(1).getBase_stat())));
        pokemonStatsDefense.setText((String.valueOf(selectedPokemon.getStats().get(2).getBase_stat())));
        pokemonStatsSPAttack.setText((String.valueOf(selectedPokemon.getStats().get(3).getBase_stat())));
        pokemonStatsSPDefense.setText((String.valueOf(selectedPokemon.getStats().get(4).getBase_stat())));
        pokemonStatsSpeed.setText((String.valueOf(selectedPokemon.getStats().get(5).getBase_stat())));

    }
}