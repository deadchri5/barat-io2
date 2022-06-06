package com.example.baratio2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EmpleadosFragment extends Fragment {

    View root;

    public EmpleadosFragment() {
        // Required empty public constructor
    }

    public static EmpleadosFragment newInstance() {
        EmpleadosFragment fragment = new EmpleadosFragment();
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
        root = inflater.inflate(R.layout.fragment_empleados, container, false);
        init();
        return root;
    }

    public void init() {
        //Agregar la lógica aquí
        //Para sacar contexto de un fragment en lugar de poner "this", pones "root.getContext()"
        //o root.findViewById(R.id.LO_QUE_SEA)
    }
}