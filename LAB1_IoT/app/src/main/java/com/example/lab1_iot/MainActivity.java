package com.example.lab1_iot;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textViewTeleMath = findViewById(R.id.textView2);
        registerForContextMenu(textViewTeleMath);

        Button buttonIndicaciones = findViewById(R.id.button);
        buttonIndicaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Indicacionesview.class);
                startActivity(intent);
            }
        });

        Button buttonCalcular = findViewById(R.id.button2);
        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Calcularview.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Cambia el color");
        menu.add(0, v.getId(), 0, "Azul");
        menu.add(0, v.getId(), 0, "Verde");
        menu.add(0, v.getId(), 0, "Rojo");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        TextView textViewTeleMath = findViewById(R.id.textView2);

        if(item.getTitle().equals("Blue")) {
            textViewTeleMath.setTextColor(Color.BLUE);
            return true;
        }
        else if(item.getTitle().equals("Green")) {
            textViewTeleMath.setTextColor(Color.GREEN);
            return true;
        }
        else if(item.getTitle().equals("Red")) {
            textViewTeleMath.setTextColor(Color.RED);
            return true;
        }
        else {
            return super.onContextItemSelected(item);
        }
    }

}
