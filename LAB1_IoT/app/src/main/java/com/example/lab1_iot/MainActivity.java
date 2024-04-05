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
        if (v.getId() == R.id.textView2) {
            getMenuInflater().inflate(R.menu.context_menu, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        TextView textViewTeleMath = findViewById(R.id.textView2);
        int colorResId;

        switch (id) {
            case R.id.colorblue:
                colorResId = R.color.blue;
                break;
            case R.id.colorgreen:
                colorResId = R.color.green;
                break;
            case R.id.colorred:
                colorResId = R.color.red;
                break;
            default:
                return super.onContextItemSelected(item);
        }
        textViewTeleMath.setTextColor(ContextCompat.getColor(this, colorResId));

        return true;
    }

}
