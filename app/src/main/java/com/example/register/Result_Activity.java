package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

//nous souhaitons afficher le nom de la ville et
// le nombre d'annonces dans des TextView dans notre
// activité ResultActivity

class Result_Activity extends AppCompatActivity{

    private TextView textViewCityName;
    private TextView textViewAnnounceCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);

        textViewCityName = findViewById(R.id.textView_city_name);
        textViewAnnounceCount = findViewById(R.id.textView_announce_count);

        Intent intent = getIntent();
        if (intent != null) {
            String cityName = intent.getStringExtra("cityName");
            int announceCount = intent.getIntExtra("announceCount", 0);

            textViewCityName.setText(cityName);
            textViewAnnounceCount.setText("Nombre d'annonces : " + announceCount);
        }
    }
}


