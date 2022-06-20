package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class CrearProductoDialogFragment extends DialogFragment {
    View root;
    EditText nombre, cantidad, precio, descripcion,marca;
    Button agregar;
    InventarioFragment inventarioFragment;

    public CrearProductoDialogFragment() {
        // Required empty public constructor
    }

    public CrearProductoDialogFragment(InventarioFragment inventarioFragment) {
        // Required empty public constructor
        this.inventarioFragment = inventarioFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_crear_producto, container, false);
        nombre = root.findViewById(R.id.txtNombre);
        cantidad = root.findViewById(R.id.txtCantidad);
        precio = root.findViewById(R.id.txtPrecio);
        descripcion = root.findViewById(R.id.txtDescripcion);
        marca = root.findViewById(R.id.txtMarca);
        agregar = root.findViewById(R.id.btnRegistrar);
        agregar.setOnClickListener(view -> {
            SharedPreferences sharedPreferences =
                    this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String json = sharedPreferences.getString("productoList", "");
            ArrayList<Producto> productoList = new Gson()
                    .fromJson(json, new TypeToken<ArrayList<Producto>>() { }.getType());
            String sNombre = nombre.getText().toString();
            String sCantidad = cantidad.getText().toString();
            String sPrecio = precio.getText().toString();
            String sDescripcion = descripcion.getText().toString();
            String sMarca = marca.getText().toString();
            productoList.add(new Producto(sNombre+sPrecio, sNombre, sCantidad,
                    sMarca, sDescripcion, sPrecio));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            json = new Gson().toJson(productoList);
            prefsEditor.putString("productoList", json);
            prefsEditor.commit();
            inventarioFragment.cargarDatos();
            this.dismiss();
        });
        return root;
    }
}