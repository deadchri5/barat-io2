package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CrearUsuarioDialogFragment extends DialogFragment {

    View root;
    EditText nombre, correo, contraseña;
    Spinner tipoDeUsuario;
    Button agregar;
    EmpleadosFragment empleadosFragment;

    public CrearUsuarioDialogFragment() {
        // Required empty public constructor
    }

    public CrearUsuarioDialogFragment(EmpleadosFragment empleadosFragment) {
        this.empleadosFragment = empleadosFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_crear_usuario, container, false);
        init();
        return root;
    }
    public void init(){
        nombre = root.findViewById(R.id.txtNombre);
        correo = root.findViewById(R.id.txtCorreo);
        contraseña = root.findViewById(R.id.txtContrasena);
        tipoDeUsuario = root.findViewById(R.id.spinnerUsuario);
        agregar = root.findViewById(R.id.btnRegistrar);
        agregar.setOnClickListener(view -> {
            guardarPreferencias();
        });
    }

    public void guardarPreferencias() {
        SharedPreferences sharedPreferences =
                this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String nombre = this.nombre.getText().toString();
        String correo = this.correo.getText().toString();
        String contraseña = this.contraseña.getText().toString();
        String tipoUsuraio = this.tipoDeUsuario.getSelectedItem().toString();
        String id = Integer.toString((int)Math.floor(Math.random()*(10000-0+1)+0));
        System.out.println(nombre);
        String json = sharedPreferences.getString("userList", "");
        ArrayList<Usuario> userList = new Gson()
                .fromJson(json, new TypeToken<ArrayList<Producto>>() { }.getType());
        userList.add(new Usuario(id, nombre, tipoUsuraio, correo, contraseña));
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        json = new Gson().toJson(userList);
        System.out.println(json);
        prefsEditor.putString("userList", json);
        prefsEditor.commit();
        empleadosFragment.cargarDatos();
        this.dismiss();
    }
}