package com.example.dexplorer.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.dexplorer.R;
import com.example.dexplorer.adapter.AdapterAllPoke;
import com.example.dexplorer.api.PokeService;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonListResponse;
import com.example.dexplorer.model.Species;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
//    private Button buttonEnviar;
    private Button buttonBuscarPokemons;
    private Button buttonTest;
    private List<Pokemom> listaPokemons = new ArrayList();
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        retrofit = new Retrofit.Builder().baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarListaPokemon();
        buttonBuscarPokemons = findViewById(R.id.buttonBuscarPokemons);


        buttonBuscarPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllPokemonsActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("ListaPokemons", (ArrayList<? extends Parcelable>) listaPokemons);
                intent.putExtras(bundle);


                startActivity(intent);
            }
        });
    }



    private void recuperarListaPokemon(){
        PokeService service = retrofit.create((PokeService.class));
        Call<PokemonListResponse> call = service.getPokemonList();
        call.enqueue(new Callback<PokemonListResponse>() {
            @Override
            public void onResponse(Call<PokemonListResponse> call, Response<PokemonListResponse> response) {
                Log.d("resp", "Req 1 ok" );
                if (response.isSuccessful()){
                    PokemonListResponse pokemonListResponse = response.body();
                    if (pokemonListResponse != null){



                        for (Pokemom pokemom: pokemonListResponse.getResults()){
                            Call<Pokemom> pokemonDetailsCall = service.getPokemonDetails(pokemom.getName());

                            pokemonDetailsCall.enqueue(new Callback<Pokemom>() {
                                @Override
                                public void onResponse(Call<Pokemom> call, Response<Pokemom> response) {
                                    if (response.isSuccessful()){
                                        Log.d("resp", "ID: "+response.body().getId()+"  Nome:" +  response.body().getName()
                                                +" FrontSprite:"+response.body().getSprites().getFrontDefault()
                                                +" Tipo Slot 1: "+response.body().getTypes().get(0).getType().getName()

                                        );
                                        Pokemom pokemonDetails = response.body();
//                                        Log.d("resp", "  id:" +PokemonDetails.getId());
                                        pokemom.setDetails(pokemonDetails.getId(), pokemonDetails.getSprites(), pokemonDetails.getTypes(), pokemonDetails.getHeight(),
                                                pokemonDetails.getWeight(), pokemonDetails.getAbilities(), pokemonDetails.getStats(), pokemonDetails.getSpecies());


                                    }
                                }

                                @Override
                                public void onFailure(Call<Pokemom> call, Throwable t) {

                                }
                            });


                            listaPokemons.add(pokemom);

                        }

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

