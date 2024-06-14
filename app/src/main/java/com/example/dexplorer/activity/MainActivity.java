package com.example.dexplorer.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dexplorer.R;

public class MainActivity extends AppCompatActivity {
//    private Button buttonEnviar;
    private Button buttonBuscarPokemons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonBuscarPokemons = findViewById(R.id.buttonBuscarPokemons);
        buttonBuscarPokemons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AllPokemonsActivity.class);
                startActivity(intent);
            }
        });



//        buttonEnviar = findViewById(R.id.buttonEnviar);
//        buttonEnviar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), SegundaActivity.class);
//                intent.putExtra("nome", "Evynne");
//                intent.putExtra("idade", 23);
//
//                startActivity(intent);
//            }
//        });
    }
}