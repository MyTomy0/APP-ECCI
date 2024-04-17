package com.example.ecci;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText Email, Password;
    Button Login;
    String email, password;

    @Override
    protected void onCreate(Bundle SavedInstanceState) {
        super.onCreate(SavedInstanceState);
        setContentView(R.layout.activity_login);
        Email = findViewById(R.id.edtEmail);
        Password = findViewById(R.id.edtPassword);
        Login = findViewById(R.id.btnLogin);

        recuperarPreferencias();

        Login.setOnClickListener(view -> {
            email = Email.getText().toString();
            password = Password.getText().toString();
            if (!email.isEmpty() && !password.isEmpty()) {
                if (email.equals("adminecci@ecci.edu.co") && password.equals("adminecci")) {
                    Intent intent = new Intent(getApplicationContext(), AdministratorActivity.class);
                    startActivity(intent);
                } else {
                    usuario();
                }
            } else {
                Toast.makeText(LoginActivity.this, "No se permiten campos vacios", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void usuario() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.20.34/BasesDeDatos/validarusuario.php",
                response -> {
                    if (response.equals("success")) {
                        guardarPreferencias();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(LoginActivity.this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_SHORT).show()) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Email", email);
                parametros.put("password", password);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("Email",email);
        editor.putString("Password",password);
        editor.putBoolean("sesion",true);
        editor.apply();
    }

    private void recuperarPreferencias() {
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        Email.setText(preferences.getString("email","adminecci@ecci.edu.co"));
        Password.setText(preferences.getString("password","adminecci"));
    }

}