package com.example.dexplorer.activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
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
    ImageButton catchPokeButton;

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
        catchPokeButton = findViewById(R.id.pokeballButton);
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

        //modal
        catchPokeButton.setOnClickListener(v -> showNameInputDialog());

    }

    private void showNameInputDialog(){
        //inflar o layout personalizado
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_name_input, null);

        //Criar o alert dialog
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setView(dialogView);

        //Capturar o editText do layout inflado
        EditText editTextName = dialogView.findViewById(R.id.editTextName);

        //configurar os botões do dialog
        dialogBuilder
                .setCancelable(true)
                .setPositiveButton("Salvar", (dialog, id)->{
                    //Ação quando o botão salvar for clicado
                    String enteredName = editTextName.getText().toString();
                    if (!enteredName.isEmpty()){
                        Toast.makeText(this,"Nome inserido: " + enteredName, Toast.LENGTH_SHORT).show();
                        //adicionar ao banco aqui
                    }else{
                        Toast.makeText(this, "Por favor insira um nome.", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Cancelar", (dialog, id)->dialog.cancel());
        //criar e exibir o dialog
        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

    }

}