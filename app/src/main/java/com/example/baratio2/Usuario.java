package com.example.baratio2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Usuario {
    private String id, nombre, tipoUsuario, correo, pass;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String tipoUsuario, String correo, String pass) {
        this.id = id;
        this.nombre = nombre;
        this.tipoUsuario = tipoUsuario;
        this.correo = correo;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario producto = (Usuario) o;
        return  getId().equals(producto.getId()) &&
                getNombre().equals(producto.getNombre()) &&
                getTipoUsuario().equals(producto.getTipoUsuario()) &&
                getCorreo().equals(producto.getCorreo()) &&
                getPass().equals(producto.getPass());
    }

    public static DiffUtil.ItemCallback<Usuario> itemCallback =
            new DiffUtil.ItemCallback<Usuario>() {
                @Override
                public boolean areItemsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Usuario oldItem, @NonNull Usuario newItem) {
                    return oldItem.equals(newItem);
                }
            };
}