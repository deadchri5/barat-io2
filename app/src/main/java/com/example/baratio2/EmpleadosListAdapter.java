package com.example.baratio2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baratio2.databinding.UsuarioRowBinding;

public class EmpleadosListAdapter extends ListAdapter<Usuario, EmpleadosListAdapter.UsuarioViewHolder> {

    protected EmpleadosListAdapter() {
        super(Usuario.itemCallback);
    }

    @NonNull
    @Override
    public UsuarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        UsuarioRowBinding usuarioRowBinding = UsuarioRowBinding.inflate(layoutInflater,
                parent, false);
        return new UsuarioViewHolder(usuarioRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioViewHolder holder, int position) {
        Usuario usuario = getItem(position);
        holder.usuarioRowBinding.setUsuario(usuario);
    }

    class UsuarioViewHolder extends RecyclerView.ViewHolder{

        UsuarioRowBinding usuarioRowBinding;

        public UsuarioViewHolder(UsuarioRowBinding binding) {
            super(binding.getRoot());
            usuarioRowBinding = binding;
        }
    }

}