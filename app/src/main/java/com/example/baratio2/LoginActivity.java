package com.example.baratio2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button salir, iniciar;
    SharedPreferences sharedPreferences;
    ArrayList<Usuario> usuarioList;
    EditText user, pass;
    boolean acceso = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Gson gson = new Gson();
        sharedPreferences = this.getSharedPreferences("MySharedPref", MODE_PRIVATE);
        if (sharedPreferences.contains("userList")) {
            String json = sharedPreferences.getString("userList", "");
            usuarioList = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>() {
            }.getType());
        }
        salir = findViewById(R.id.salir);
        iniciar = findViewById(R.id.iniciar);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        user.setText("root");
        pass.setText("admin");

salir.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Toast.makeText(LoginActivity.this, "Se cerro la aplicacion", Toast.LENGTH_SHORT).show();
        finish();
    }
});


iniciar.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        String usr = user.getText().toString();
        String psw = pass.getText().toString();
        for (int i = 0; i < usuarioList.size(); i++) {
            if (usuarioList.get(i).getNombre().equals(usr) && usuarioList.get(i).getPass().equals(psw)) {
                Toast.makeText(LoginActivity.this, "Bienvenido " +
                        usuarioList.get(i).getNombre(), Toast.LENGTH_SHORT).show();
                acceso = true;
            } else if (user.getText().toString().equals("root") && pass.getText().toString().equals("admin")) {
                Toast.makeText(LoginActivity.this, "Bienvenido ROOT", Toast.LENGTH_SHORT).show();
                acceso = true;
            }
        }
        if (acceso) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(LoginActivity.this, "Verifique sus credenciales",
                    Toast.LENGTH_SHORT).show();
        }
    }
});

    }
}