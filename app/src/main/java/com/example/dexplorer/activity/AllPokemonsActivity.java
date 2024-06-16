package com.example.dexplorer.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dexplorer.R;
import com.example.dexplorer.adapter.AdapterAllPoke;
import com.example.dexplorer.api.PokeService;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonListResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllPokemonsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Pokemom> listaPokemons = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_pokemons);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle bundle = getIntent().getExtras();
        listaPokemons = bundle.getParcelableArrayList("ListaPokemons");

//        for (Pokemom pokemom: listaPokemons){
//            if (pokemom.getSprites().getFrontDefault() != null){
//                Log.d("AllPokemonsActivity",pokemom.getSprites().getFrontDefault());
//            }
//
//        }


        Log.d("AllPokemonsActivity","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        recyclerView = findViewById(R.id.rv_allPokemons);



        AdapterAllPoke adapter =new AdapterAllPoke(listaPokemons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }


}