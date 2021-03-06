package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class InventarioFragment extends Fragment {

    private InventarioListAdapter inventarioListAdapter;
    private RecyclerView lstProductos;
    private SharedPreferences sharedPreferences;
    private EditText txtBuscar, txtPrecio, txtMarca;
    ArrayList<Producto> productoList;
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

    public void cargarDatos() {
        inventarioListAdapter = new InventarioListAdapter();
        productoList = new ArrayList<>();
        Gson gson = new Gson();
        if (!sharedPreferences.contains("productoList")) {
            productoList.add(new Producto("vd52", "Verdura", "5", "La Granja",
                    "Verdura Fresca", "120"));
            productoList.add(new Producto("pp33", "Pechuga de Pollo", "55",
                    "La Granja", "Pechuga de Pollo", "244"));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String json = gson.toJson(productoList);
            prefsEditor.putString("productoList", json);
            prefsEditor.commit();
        } else {
            String json = sharedPreferences.getString("productoList", "");
            productoList = gson.fromJson(json, new TypeToken<ArrayList<Producto>>() {
            }.getType());
        }
        inventarioListAdapter.submitList(productoList);
        lstProductos.setAdapter(inventarioListAdapter);
        inventarioListAdapter.notifyDataSetChanged();
    }

    public void init() {
        lstProductos = root.findViewById(R.id.lstUsuarios);
        txtBuscar = root.findViewById(R.id.txtBuscar);
        txtPrecio = root.findViewById(R.id.txtPrecio);
        txtMarca = root.findViewById(R.id.txtMarca);
        lstProductos.setLayoutManager(new LinearLayoutManager(root.getContext()));
        cargarDatos();
        ImageButton btnCrear = root.findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(view -> {
            final CrearProductoDialogFragment crearDialog = new CrearProductoDialogFragment(this);
            //fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
            //crearDialog.setContentView(R.layout.fragment_crear_producto);
            crearDialog.setCancelable(true);
            crearDialog.show(getActivity().getSupportFragmentManager(), "dialog");
        });
        ImageButton btnBuscar = root.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(view -> {
            String sBuscar = txtBuscar.getText().toString();
            String sPrecio = txtPrecio.getText().toString();
            String sMarca = txtMarca.getText().toString();
            if (sBuscar.isEmpty() && sPrecio.isEmpty() && sMarca.isEmpty()) {
                inventarioListAdapter.submitList(productoList);
                inventarioListAdapter.notifyDataSetChanged();
            } else {
                ArrayList<Producto> filteredProductoList = new ArrayList<>();

                for (Producto p : productoList) {
                    boolean bNombre = true, bPrecio = true, bMarca = true;
                    if (!sMarca.isEmpty()) {
                        bMarca = false;
                        if (p.getMarca().toLowerCase().contains(sMarca.toLowerCase())) {
                            bMarca = true;
                        }
                    }
                    if (!sBuscar.isEmpty()) {
                        bNombre = false;
                        if (p.getNombre().toLowerCase().contains(sBuscar.toLowerCase())) {
                            bNombre = true;
                        }
                    }
                    if (!sPrecio.isEmpty()) {
                        float precioP = Float.parseFloat(p.getPrecio());
                        float precioS = Float.parseFloat(sPrecio);
                        bPrecio = false;
                        if (precioP >= precioS) {
                            bPrecio = true;
                        }
                    }
                    if (bNombre && bPrecio && bMarca) {
                        filteredProductoList.add(p);
                    }
                }
                inventarioListAdapter.submitList(filteredProductoList);
                inventarioListAdapter.notifyDataSetChanged();
            }
        });
    }
}