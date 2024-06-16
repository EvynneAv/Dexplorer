package com.example.dexplorer.api;

import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokeService {
    @GET("pokemon?limit=15")
    Call<PokemonListResponse> getPokemonList();

    @GET("pokemon/{pokemon}")
    Call<Pokemom> getPokemonDetails(@Path("pokemon") String PokemonName);
}
