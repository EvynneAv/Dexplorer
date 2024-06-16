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
    private Retrofit retrofit;
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

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        recuperarListaPokemon();

    }

    private void recuperarListaPokemon(){
        PokeService service = retrofit.create((PokeService.class));
        Call<PokemonListResponse> call = service.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful()){
                    PokemonListResponse pokemonListResponse = response.body();
                    if (pokemonListResponse != null){
                        for (Pokemom pokemom: pokemonListResponse.getResults()){
                            Call<Pokemom> pokemonDetailsCall = service.getPokemonDetails(pokemom.getName());

                            pokemonDetailsCall.enqueue(new Callback<Pokemom>() {
                                @Override
                                public void onResponse(Call<Pokemom> call, Response<Pokemom> response) {
                                    if (response.isSuccessful()){
                                        Pokemom PokemonDetails = response.body();
//                                        Log.d("resp", "  id:" +PokemonDetails.getId());
                                        pokemom.setDetails(PokemonDetails.getId());

                                    }
                                }

                                @Override
                                public void onFailure(Call<Pokemom> call, Throwable t) {

                                }
                            });
//                            Log.d("resultado", "Resultado: "+pokemom.getName() +"  id:" +pokemom.getId());

                            listaPokemons.add(pokemom);

                        }
                        recyclerView = findViewById(R.id.rv_allPokemons);



                        AdapterAllPoke adapter =new AdapterAllPoke(listaPokemons);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setHasFixedSize(true);
                        recyclerView.setAdapter(adapter);
                    }else{

                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {

            }
        });
    }

}