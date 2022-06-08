package com.example.baratio2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class UsuarioFragment extends Fragment {

    private InventarioListAdapter inventarioListAdapter;
    private RecyclerView lstProductos;
    View root;

    public UsuarioFragment() {
        // Required empty public constructor
    }

    public static UsuarioFragment newInstance() {
        UsuarioFragment fragment = new UsuarioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
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
        productoList.add(new Producto("vd52", "Verdura", "5", "La Granja",
                "Verdura Fresca", 120));
        productoList.add(new Producto("pp33", "Pechuga de Pollo", "55",
                "La Granja", "Pechuga de Pollo", 244));
        inventarioListAdapter.submitList(productoList);
        lstProductos = root.findViewById(R.id.lstProductos);
        lstProductos.setLayoutManager(new LinearLayoutManager(root.getContext()));
        lstProductos.setAdapter(inventarioListAdapter);
        inventarioListAdapter.notifyDataSetChanged();
    }
}