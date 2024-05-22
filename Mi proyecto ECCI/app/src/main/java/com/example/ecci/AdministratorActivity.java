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
public class AdministratorActivity extends AppCompatActivity {

    EditText Id, Name, LastName, Code, Date, Email, Password;
    Button Add, Lookfor, Edit, Eliminate, Cerrar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_administrator);

        Id= findViewById(R.id.edtId1);
        Name= findViewById(R.id.edtName1);
        LastName= findViewById(R.id.edtLastName1);
        Code=findViewById(R.id.edtCode1);
        Date= findViewById(R.id.edtDate1);
        Email= findViewById(R.id.edtEmail1);
        Password= findViewById(R.id.edtPassword1);
        Add= findViewById(R.id.btnAdd);
        Lookfor= findViewById(R.id.btnLookfor);
        Edit= findViewById(R.id.btnEdit);
        Eliminate= findViewById(R.id.btnEliminate);
        Cerrar= findViewById(R.id.btnCerrar);

        Add.setOnClickListener(v -> ejecutarServicio("http://192.168.20.34/BasesDeDatos/post.php"));

        Lookfor.setOnClickListener(v -> buscarId());

        Edit.setOnClickListener(v -> {
            ejecutarServicio("http://192.168.20.34/BasesDeDatos/put.php");
            Toast.makeText(getApplicationContext(),"SE MODIFICÓ CORRECTAMENTE",Toast.LENGTH_SHORT).show();
        });

        Eliminate.setOnClickListener(v -> eliminarId());

        Cerrar.setOnClickListener(view -> {
            SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
            preferences.edit().clear().apply();

            Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            fileList();
        });
    }

    private void ejecutarServicio(String URL) {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL,
                response -> Toast.makeText(getApplicationContext(), "OPERACION EXITOSA",
                        Toast.LENGTH_SHORT).show(), error -> Toast.makeText(getApplicationContext(),
                error.toString(),Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parametros= new HashMap<>();
                parametros.put("id",Id.getText().toString());
                parametros.put("name",Name.getText().toString());
                parametros.put("lastname",LastName.getText().toString());
                parametros.put("code",Code.getText().toString());
                parametros.put("date",Date.getText().toString());
                parametros.put("email",Email.getText().toString());
                parametros.put("password",Password.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void buscarId() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                "http://192.168.20.34/BasesDeDatos/get.php?id=" + Id.getText(), response -> {
            if (response.startsWith("{")) {
            } else {
                String[] data = response.split(",");
                if (data.length >= 6) {
                    Name.setText(data[0].trim());
                    LastName.setText(data[1].trim());
                    Code.setText(data[2].trim());
                    Date.setText(data[3].trim());
                    Email.setText(data[4].trim());
                    Password.setText(data[5].trim());
                    Toast.makeText(getApplicationContext(), "DATOS COMPLETOS", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "DATOS INCOMPLETOS", Toast.LENGTH_SHORT).show();
                }
            }
        }, error -> Toast.makeText(getApplicationContext(), "ERROR DE CONEXIÓN", Toast.LENGTH_SHORT).show());
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private void eliminarId() {
        StringRequest stringRequest=new StringRequest(Request.Method.POST,
                "http://192.168.20.34/BasesDeDatos/delete.php", response -> {
            Toast.makeText(getApplicationContext(), "EL ID FUE ELIMINADO", Toast.LENGTH_SHORT).show();
            limpiarFormulario();
        }, error -> Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show()){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> parametros= new HashMap<>();
                parametros.put("id",Id.getText().toString());
                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void limpiarFormulario(){
        Id.setText("");
        Name.setText("");
        LastName.setText("");
        Code.setText("");
        Date.setText("");
        Email.setText("");
        Password.setText("");
    }
}
