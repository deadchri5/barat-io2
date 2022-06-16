package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class InventarioFragment extends Fragment {

    private InventarioListAdapter inventarioListAdapter;
    private RecyclerView lstProductos;
    private SharedPreferences sharedPreferences;
    View root;

    public InventarioFragment() {
        // Required empty public constructor
    }

    public static InventarioFragment newInstance() {
        InventarioFragment fragment = new InventarioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
        sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_inventario, container, false);
        init();
        return root;
    }
    
    public void init() {
        inventarioListAdapter = new InventarioListAdapter();
        ArrayList<Producto> productoList = new ArrayList<>();
        Gson gson = new Gson();
        if(!sharedPreferences.contains("productoList")){
            productoList.add(new Producto("vd52", "Verdura", "5", "La Granja",
                    "Verdura Frescaxd", 120));
            productoList.add(new Producto("pp33", "Pechuga de Pollo", "55",
                    "La Granja", "Pechuga de Pollo", 244));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String json = gson.toJson(productoList);
            prefsEditor.putString("productoList", json);
            prefsEditor.commit();
        }
        else{
            String json = sharedPreferences.getString("productoList", "");
            productoList = gson.fromJson(json, new TypeToken<ArrayList<Producto>>() {
            }.getType());
        }
        inventarioListAdapter.submitList(productoList);
        lstProductos = root.findViewById(R.id.lstProductos);
        lstProductos.setLayoutManager(new LinearLayoutManager(root.getContext()));
        lstProductos.setAdapter(inventarioListAdapter);
        inventarioListAdapter.notifyDataSetChanged();
        ImageButton button= root.findViewById(R.id.btnCrear);
        button.setOnClickListener(view -> {
            final CrearProductoDialog crearDialog = new CrearProductoDialog();
            //fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
            //crearDialog.setContentView(R.layout.fragment_crear_producto);
            crearDialog.setCancelable(true);
            crearDialog.show(getActivity().getSupportFragmentManager(), "dialog");
        });
    }
}