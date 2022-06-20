package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ModificarProductoDialogFragment extends DialogFragment {
    View root;
    EditText nombre, cantidad, precio, descripcion;
    Spinner marca;
    Button agregar;
    InventarioListAdapter.InventarioViewHolder viewHolder;
    Producto producto;

    public ModificarProductoDialogFragment() {
        // Required empty public constructor
    }

    public ModificarProductoDialogFragment(InventarioListAdapter.InventarioViewHolder viewHolder,
                                           Producto producto) {
        // Required empty public constructor
        this.viewHolder = viewHolder;
        this.producto = producto;
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
        TextView titulo = root.findViewById(R.id.txtTitulo);
        titulo.setText(R.string.modificar_producto);
        nombre = root.findViewById(R.id.txtNombre);
        nombre.setText(producto.getNombre());
        cantidad = root.findViewById(R.id.txtCantidad);
        cantidad.setText(producto.getCantidad());
        precio = root.findViewById(R.id.txtPrecio);
        precio.setText(producto.getPrecio());
        descripcion = root.findViewById(R.id.txtDescripcion);
        descripcion.setText(producto.getDescripcion());
        marca = root.findViewById(R.id.spinnerMarca);
        agregar = root.findViewById(R.id.btnRegistrar);
        agregar.setText(R.string.modificar);
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
            //Corregir cuando esten las marcas
            //String sMarca = marca.getSelectedItem().toString();
            int position = viewHolder.getAdapterPosition();
            productoList.set(position, new Producto(sNombre+sPrecio, sNombre, sCantidad,
                    producto.getMarca(), sDescripcion, sPrecio));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            json = new Gson().toJson(productoList);
            prefsEditor.putString("productoList", json);
            prefsEditor.commit();
            viewHolder.inventarioListAdapter.submitList(productoList);
            viewHolder.inventarioListAdapter.notifyItemChanged(position);
            this.dismiss();
        });
        return root;
    }
}