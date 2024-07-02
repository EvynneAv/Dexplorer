package com.example.dexplorer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
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
import com.example.dexplorer.model.PokemonType;
import com.example.dexplorer.utils.RecyclerItemClickListener;

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



        Log.d("AllPokemonsActivity","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        recyclerView = findViewById(R.id.rv_allPokemons);



        AdapterAllPoke adapter =new AdapterAllPoke(listaPokemons);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), listaPokemons.get(position).getName(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), PokemonInDetailsActivity.class);
                        Bundle bundle = new Bundle();

                        bundle.putParcelable("pokemon", listaPokemons.get(position));
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(), "Click longo" +
                                "", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    }
                }
                )
        );

    }


}