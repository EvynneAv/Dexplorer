package com.example.dexplorer.api;

import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeService {
    @GET("pokemon")
    Call<PokemonListResponse> getPokemonList();
}
