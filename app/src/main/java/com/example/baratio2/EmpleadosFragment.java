package com.example.baratio2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EmpleadosFragment extends Fragment {

    private EmpleadosListAdapter empleadosListAdapter;
    private RecyclerView lstProductos;
    View root;

    public EmpleadosFragment() {
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
        empleadosListAdapter = new EmpleadosListAdapter();
        ArrayList<Usuario> usuarioList = new ArrayList<>();
        usuarioList.add(new Usuario("669", "Aldo", "1",
                "aldo@maildrop.cc","pass"));
        usuarioList.add(new Usuario("777", "Adolfo", "2",
                "bofo@maildrop.cc", "pass"));
        usuarioList.add(new Usuario("666", "fabian", "2",
                "elman@maildrop.cc", "fabian123"));
        empleadosListAdapter.submitList(usuarioList);
        lstProductos = root.findViewById(R.id.lstProductos);
        lstProductos.setLayoutManager(new LinearLayoutManager(root.getContext()));
        lstProductos.setAdapter(empleadosListAdapter);
        empleadosListAdapter.notifyDataSetChanged();
    }
}