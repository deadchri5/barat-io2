package com.example.baratio2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class addProductFragment extends Fragment {
    View root;
    public addProductFragment() {
        // Required empty public constructor
    }

    public static addProductFragment newInstance() {
        addProductFragment fragment = new addProductFragment();
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
        root = inflater.inflate(R.layout.fragment_add_user, container, false);
        init();
        return root;
    }
    public void init(){
        //Logica
    }
}