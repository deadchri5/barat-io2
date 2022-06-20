package com.example.baratio2;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baratio2.databinding.InventarioRowBinding;
import com.example.baratio2.databinding.UsuarioRowBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class EmpleadosListAdapter extends ListAdapter<Usuario, EmpleadosListAdapter.UsuarioViewHolder> {

    protected EmpleadosListAdapter() {
        super(Usuario.itemCallback);
    }

    public class UsuarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        EmpleadosListAdapter empleadosListAdapter;
        Context context;
        UsuarioRowBinding usuarioRowBinding;
        ImageButton btnDelete, btnModificar;
        SharedPreferences sharedPreferences;

        public UsuarioViewHolder(UsuarioRowBinding binding,
                                    EmpleadosListAdapter EmpleadosListAdapter) {
            super(binding.getRoot());
            context = binding.getRoot().getContext();
            usuarioRowBinding = binding;
            btnDelete = binding.getRoot().findViewById(R.id.btnEliminar);
            btnModificar = binding.getRoot().findViewById(R.id.btnModificarUsuario);
            this.empleadosListAdapter = empleadosListAdapter;

        }

        public void setOnClickListeners(Usuario usuario) {
            usuarioRowBinding.setUsuario(usuario);
            btnDelete.setOnClickListener(this);
            btnModificar.setOnClickListener(view -> {
                final ModificarUsuarioDialogFragment modificarDialog =
                        new ModificarUsuarioDialogFragment(this, usuario);
                modificarDialog.setCancelable(true);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                modificarDialog.show(activity.getSupportFragmentManager(), "dialog");
            });
        }

        @Override
        public void onClick(View view) {
            Gson gson = new Gson();
            ArrayList<Usuario> usuarioList;
            Usuario usuario = usuarioRowBinding.getUsuario();
            sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
            if (view.getId() == R.id.btnEliminar) {
                if (sharedPreferences.contains("userList")) {
                    int position = 0;
                    String productID = usuario.getId();
                    System.out.println(productID);
                    String json = sharedPreferences.getString("userList", "");
                    usuarioList = gson.fromJson(json, new TypeToken<ArrayList<Usuario>>() {
                    }.getType());
                    for (int i = 0; i < usuarioList.size(); i++) {
                        if (usuarioList.get(i).getId().equals(productID)) {
                            position = i;
                            Toast.makeText(context, "Usuario " + usuarioList.get(i).getNombre() + " Eliminado.", Toast.LENGTH_SHORT).show();
                            usuarioList.remove(i);
                        }
                    }
                    String newJson = gson.toJson(usuarioList);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    prefsEditor.putString("userList", newJson);
                    prefsEditor.commit();
                    submitList(usuarioList);
                    notifyItemRemoved(position);
                } else {
                    Toast.makeText(context, "No hay usuarios que eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UsuarioRowBinding usuarioRowBinding = UsuarioRowBinding.inflate(layoutInflater,
                parent, false);
        return new UsuarioViewHolder(usuarioRowBinding, this);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = getItem(position);
        //holder.usuarioRowBinding.setUsuario(usuario);
        holder.setOnClickListeners(usuario);
    }

}