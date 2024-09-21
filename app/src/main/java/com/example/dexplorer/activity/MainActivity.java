package com.example.dexplorer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.dexplorer.R;
import com.example.dexplorer.activity.AllPokemonsActivity;
import com.example.dexplorer.api.PokeService;
import com.example.dexplorer.model.Pokemom;
import com.example.dexplorer.model.PokemonListResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
    private Button buttonLogin;
    private Button buttonMyTeam;
    FirebaseAuth auth;
    GoogleSignInClient googleSignInClient;
    ImageView imgProfile;
    FirebaseUser currentUser;
    //config login google
    private final ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode()==RESULT_OK){
                Task<GoogleSignInAccount> accountTask = GoogleSignIn.getSignedInAccountFromIntent(result.getData());
                try {
                    GoogleSignInAccount signInAccount = accountTask.getResult(ApiException.class);
                    AuthCredential authCredential = GoogleAuthProvider.getCredential(signInAccount.getIdToken(), null);
                    auth.signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                auth = FirebaseAuth.getInstance();
                                Glide.with(MainActivity.this).load(Objects.requireNonNull(auth.getCurrentUser()).getPhotoUrl()).into(imgProfile);
                                Toast.makeText(MainActivity.this, "Logado com sucesso", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this, "Falha ao logar: " + task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    });




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

        //Login no google
        FirebaseApp.initializeApp(this);
        imgProfile = findViewById(R.id.profileimage);

        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.client_id))
                        .requestEmail()
                                .build();
        googleSignInClient = GoogleSignIn.getClient(MainActivity.this, options);
        auth = FirebaseAuth.getInstance();
        currentUser = null;
        SignInButton signButton = findViewById(R.id.buttonlogin);
        signButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(intent);
                currentUser = auth.getCurrentUser();
            }
        });




        buttonMyTeam = findViewById(R.id.btn_minha_equipe);

        buttonMyTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentUser != null){
                    Intent intent = new Intent(getApplicationContext(), myTeamActivity.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(MainActivity.this, "Faça Loging para ter acesso a seu time", Toast.LENGTH_SHORT).show();
                }
            }
        });


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
