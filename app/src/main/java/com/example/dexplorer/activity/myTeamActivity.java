package com.example.dexplorer.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.dexplorer.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class myTeamActivity extends AppCompatActivity {
    private static final String TAG = "MyTeamActivity";
    private FirebaseFirestore db;
    ImageView fotopoke;
    TextView apelidopoke;
    TextView especiepokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_my_team);
        // Definindo a interface
        fotopoke = findViewById(R.id.imageViewfavpokemon);
        apelidopoke = findViewById(R.id.tv_nomefav);
        especiepokemon = findViewById(R.id.tv_especiefav);

        // Instância do Firestore
        db = FirebaseFirestore.getInstance();

        // Referência ao documento específico dentro da coleção "mypokemons" e sub-coleção "equipe"
        db.collection("mypokemons")
                .document("equipe")
                .collection("equipe")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        if (!task.getResult().isEmpty()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                // Pegar os dados do documento
                                String apelido = document.getString("apelido");
                                String nome = document.getString("nome");
                                String photo = document.getString("photo");

                                // Atualizar a UI com os dados do Pokémon
                                // Aqui você pode setar esses valores em uma RecyclerView ou diretamente na sua Activity
                                updateUI(apelido, nome, photo);
                            }
                        } else {
                            Toast.makeText(this, "Nenhum Pokémon encontrado", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.e("FirestoreError", "Erro ao buscar documentos", task.getException());
                        Toast.makeText(this, "Erro ao buscar os Pokémons", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Método para atualizar a interface do usuário com os dados do Pokémon
    private void updateUI(String apelido, String nome, String photoUrl) {
        // Exemplo de como você pode exibir o Pokémon em um ImageView e TextView
        ImageView fotopoke = findViewById(R.id.imageViewfavpokemon);  // Assumindo que você tem essas views no layout
        TextView apelidopoke = findViewById(R.id.tv_nomefav);
        TextView especiepokemon = findViewById(R.id.tv_especiefav);

        Glide.with(this).load(photoUrl).into(fotopoke);
        apelidopoke.setText(apelido);
        especiepokemon.setText(nome);
    }
}