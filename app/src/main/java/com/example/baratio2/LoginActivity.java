package com.example.baratio2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button salir, iniciar;
    EditText user, pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        salir = findViewById(R.id.salir);
        iniciar = findViewById(R.id.iniciar);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.pass);
        user.setText("fabian");
        pass.setText("fabian123");

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
        if(user.getText().toString().equals("fabian") && pass.getText().toString().equals("fabian123")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(LoginActivity.this, "Verifique sus credenciales", Toast.LENGTH_SHORT).show();
        }
    }
});

    }
}