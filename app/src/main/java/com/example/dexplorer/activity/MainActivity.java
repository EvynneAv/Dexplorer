package com.example.dexplorer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.dexplorer.R;
import com.example.dexplorer.activity.AllPokemonsActivity;
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

public class MainActivity extends AppCompatActivity {
    private Button buttonBuscarPokemons;
    private List<Pokemom> listaPokemons = new ArrayList();
    private Retrofit retrofit;
    private boolean isDataLoaded = false; // Variável de controle para indicar que os dados foram carregados

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonBuscarPokemons = findViewById(R.id.buttonBuscarPokemons);
        // Desabilita o botão no início
        buttonBuscarPokemons.setEnabled(false);

        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarListaPokemon();

        buttonBuscarPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataLoaded) { // Verifica se os dados estão carregados
                    Intent intent = new Intent(getApplicationContext(), AllPokemonsActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ListaPokemons", (ArrayList<? extends Parcelable>) listaPokemons);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "Aguarde, os dados ainda estão sendo carregados!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void recuperarListaPokemon() {
        PokeService service = retrofit.create(PokeService.class);
        Call<PokemonListResponse> call = service.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                if (response.isSuccessful()) {
                    PokemonListResponse pokemonListResponse = response.body();
                    if (pokemonListResponse != null) {
                        for (Pokemom pokemom : pokemonListResponse.getResults()) {
                            Call<Pokemom> pokemonDetailsCall = service.getPokemonDetails(pokemom.getName());
                            pokemonDetailsCall.enqueue(new Callback<Pokemom>() {
                                @Override
                                public void onResponse(Call<Pokemom> call, Response<Pokemom> response) {
                                    if (response.isSuccessful()) {
                                        Pokemom pokemonDetails = response.body();
                                        pokemom.setDetails(pokemonDetails.getId(), pokemonDetails.getSprites(), pokemonDetails.getTypes(), pokemonDetails.getHeight(),
                                                pokemonDetails.getWeight(), pokemonDetails.getAbilities(), pokemonDetails.getStats(), pokemonDetails.getSpecies());
                                        listaPokemons.add(pokemom);

                                        // Se todos os dados foram carregados, habilitar o botão
                                        if (listaPokemons.size() == pokemonListResponse.getResults().size()) {
                                            isDataLoaded = true; // Atualiza o estado dos dados
                                            buttonBuscarPokemons.setEnabled(true); // Habilita o botão
                                            Toast.makeText(MainActivity.this, "Pokémons carregados!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Pokemom> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Erro ao carregar detalhes do Pokémon", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<PokemonListResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Erro ao carregar lista de Pokémons", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
