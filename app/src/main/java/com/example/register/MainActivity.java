package com.example.register;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

//Dans cette implémentation, nous initialisons un
// DBHelperAdapter dans la méthode onCreate de MainActivity.
// Lorsque l'utilisateur clique sur le bouton "Suivant",
// nous récupérons la ville sélectionnée à partir du Spinner,
// puis nous utilisons getAnnounceCountForCity de l'adaptateur de
// base de données pour obtenir le nombre d'annonces pour cette ville. Ensuite, nous
// passons ces informations à ResultActivity via un Intent pour les afficher.


public class MainActivity extends AppCompatActivity {
    private DBHelper dbAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbAdapter = new DBHelper(this);

        Button suivantButton = findViewById(R.id.button_suivant);
        suivantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupérer la ville sélectionnée
                Spinner spinnerVille = findViewById(R.id.spinner_ville);
                String selectedVille = spinnerVille.getSelectedItem().toString();

                // Passer à la nouvelle activité en affichant le nombre d'annonces pour la ville sélectionnée
                int count = dbAdapter.getAnnounceCountForCity(selectedVille);
                Intent intent = new Intent(MainActivity.this, Result_Activity.class);
                intent.putExtra("city", selectedVille);
                intent.putExtra("count", count);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.close();
    }
}