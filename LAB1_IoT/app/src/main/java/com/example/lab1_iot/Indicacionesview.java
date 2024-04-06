package com.example.lab1_iot;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class Indicacionesview extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.indicaciones_main);

        ImageButton buttonBack = findViewById(R.id.imageButton2);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Indicacionesview.this, MainActivity.class);
                startActivity(intent);
            }
        });

        Button buttonCalcular = findViewById(R.id.button3);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Indicacionesview.this, Calcularview.class);
                startActivity(intent);
            }
        });
    }
}
