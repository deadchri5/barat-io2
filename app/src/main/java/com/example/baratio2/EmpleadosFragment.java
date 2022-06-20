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

public class EmpleadosFragment extends Fragment {

    private EmpleadosListAdapter empleadosListAdapter;
    private RecyclerView lstUsuarios;
    private SharedPreferences sharedPreferences;
    private EditText txtBuscar, txtPrecio;
    ArrayList<Usuario> usuarioList;
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
        sharedPreferences = this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
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
        lstUsuarios = root.findViewById(R.id.lstUsuarios);
        txtBuscar = root.findViewById(R.id.txtBuscar);
        txtPrecio = root.findViewById(R.id.txtPrecio);
        lstUsuarios.setLayoutManager(new LinearLayoutManager(root.getContext()));
        cargarDatos();
        ImageButton btnCrear = root.findViewById(R.id.btnCrear);
        btnCrear.setOnClickListener(view -> {
            final CrearUsuarioDialogFragment crearDialog = new CrearUsuarioDialogFragment(this);
            //fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
            //crearDialog.setContentView(R.layout.fragment_crear_producto);
            crearDialog.setCancelable(true);
            crearDialog.show(getActivity().getSupportFragmentManager(), "dialog");
        });
        ImageButton btnBuscar = root.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(view -> {
            String sBuscar = txtBuscar.getText().toString();
            String sPrecio = txtPrecio.getText().toString();
            if (sBuscar.isEmpty() && sPrecio.isEmpty()) {
                empleadosListAdapter.submitList(usuarioList);
                empleadosListAdapter.notifyDataSetChanged();
            } else {
                ArrayList<Usuario> filteredUserList = new ArrayList<>();

                for (Usuario p : usuarioList) {
                    boolean bNombre = true, bPrecio = true;
                    if (!sBuscar.isEmpty()) {
                        bNombre = false;
                        if (p.getNombre().toLowerCase().contains(sBuscar.toLowerCase())) {
                            bNombre = true;
                        }
                    }
                    /*
                    if (!sPrecio.isEmpty()) {
                        float precioP = Float.parseFloat(p.getPrecio());
                        float precioS = Float.parseFloat(sPrecio);
                        bPrecio = false;
                        if (precioP >= precioS) {
                            bPrecio = true;
                        }
                    }
                    if (bNombre && bPrecio) {
                        filteredUserList.add(p);
                    }*/
                }
                empleadosListAdapter.submitList(filteredUserList);
                empleadosListAdapter.notifyDataSetChanged();
            }
        });
    }

    public void cargarDatos() {
        empleadosListAdapter = new EmpleadosListAdapter();
        usuarioList = new ArrayList<>();
        Gson gson = new Gson();
        if (!sharedPreferences.contains("userList")) {
            usuarioList.add(new Usuario("666", "Adolfo", "1", "bofo@gmail.cc",
                    "pass"));
            usuarioList.add(new Usuario("666", "El man", "1", "feivian@gmail.cc",
                    "pass"));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            String json = gson.toJson(usuarioList);
            prefsEditor.putString("userList", json);
            prefsEditor.commit();
        } else {
            String json = sharedPreferences.getString("userList", "");
            usuarioList = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>() {
            }.getType());
        }
        empleadosListAdapter.submitList(usuarioList);
        lstUsuarios.setAdapter(empleadosListAdapter);
        empleadosListAdapter.notifyDataSetChanged();
    }
}