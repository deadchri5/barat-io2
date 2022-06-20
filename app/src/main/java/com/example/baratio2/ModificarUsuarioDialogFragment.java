package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class ModificarUsuarioDialogFragment extends DialogFragment {
    View root;
    EditText nombre, correo, pass;
    Button agregar;
    Spinner tipoDeUsuario;
    EmpleadosListAdapter.UsuarioViewHolder viewHolder;
    Usuario usuario;
    String tipo;

    public ModificarUsuarioDialogFragment() {
        // Required empty public constructor
    }

    public ModificarUsuarioDialogFragment(EmpleadosListAdapter.UsuarioViewHolder viewHolder,
                                          Usuario usuario) {
        // Required empty public constructor
        this.viewHolder = viewHolder;
        this.usuario = usuario;
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
        TextView titulo = root.findViewById(R.id.txtTitulo);
        tipoDeUsuario = root.findViewById(R.id.spinnerUsuario);
        titulo.setText(R.string.modificar_usuario);
        nombre = root.findViewById(R.id.txtNombre);
        nombre.setText(usuario.getNombre());
        correo = root.findViewById(R.id.txtCorreo);
        correo.setText(usuario.getCorreo());
        pass = root.findViewById(R.id.txtContrasena);
        pass.setText(usuario.getPass());
        agregar = root.findViewById(R.id.btnRegistrar);
        agregar.setText(R.string.modificar);
        agregar.setOnClickListener(view -> {
            SharedPreferences sharedPreferences =
                    this.getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
            String json = sharedPreferences.getString("userList", "");
            ArrayList<Usuario> userList = new Gson()
                    .fromJson(json, new TypeToken<ArrayList<Usuario>>() { }.getType());
            String sNombre = nombre.getText().toString();
            String sPassword = pass.getText().toString();
            String sCorreo = correo.getText().toString();
            String sTipo = this.tipoDeUsuario.getSelectedItem().toString();
            int position = viewHolder.getAdapterPosition();
            userList.set(position, new Usuario(Integer.toString((int)Math.floor(Math.random()*(10000-0+1)+0)),sNombre, sTipo,sCorreo, sPassword));
            SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
            json = new Gson().toJson(userList);
            prefsEditor.putString("userList", json);
            prefsEditor.commit();
            viewHolder.empleadosListAdapter.submitList(userList);
            viewHolder.empleadosListAdapter.notifyItemChanged(position);
            this.dismiss();
        });
        return root;
    }
}