package com.example.baratio2;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class Producto {
    private String id, nombre, cantidad, marca, descripcion;
    private int precio;

    public Producto() {
    }

    public Producto(String id, String nombre, String cantidad, String marca, String descripcion,
                    int precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.marca = marca;
        this.descripcion = descripcion;
        this.precio = precio;
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

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return getPrecio() == producto.getPrecio() && getId().equals(producto.getId()) &&
                getNombre().equals(producto.getNombre()) &&
                getCantidad().equals(producto.getCantidad()) &&
                getMarca().equals(producto.getMarca()) &&
                getDescripcion().equals(producto.getDescripcion());
    }

    public static DiffUtil.ItemCallback<Producto> itemCallback =
            new DiffUtil.ItemCallback<Producto>() {
                @Override
                public boolean areItemsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Producto oldItem, @NonNull Producto newItem) {
                    return oldItem.equals(newItem);
                }
            };
}