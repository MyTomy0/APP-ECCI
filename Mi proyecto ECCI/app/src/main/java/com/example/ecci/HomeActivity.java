package com.example.ecci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    Button Cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Cerrar= findViewById(R.id.btnCerrar);

        Cerrar.setOnClickListener(view -> {
            SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            preferences.edit().clear().apply();

            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            fileList();
        });
    }
}
