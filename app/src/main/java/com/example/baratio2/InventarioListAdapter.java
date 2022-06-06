package com.example.baratio2;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.baratio2.databinding.InventarioRowBinding;

public class InventarioListAdapter extends ListAdapter<Producto, InventarioListAdapter.InventarioViewHolder> {

    protected InventarioListAdapter() {
        super(Producto.itemCallback);
    }

    @NonNull
    @Override
    public InventarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        InventarioRowBinding inventarioRowBinding = InventarioRowBinding.inflate(layoutInflater,
                parent, false);
        return new InventarioViewHolder(inventarioRowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioViewHolder holder, int position) {
        Producto producto = getItem(position);
        holder.inventarioRowBinding.setProducto(producto);
    }

    class InventarioViewHolder extends RecyclerView.ViewHolder{

        InventarioRowBinding inventarioRowBinding;

        public InventarioViewHolder(InventarioRowBinding binding) {
            super(binding.getRoot());
            inventarioRowBinding = binding;
        }
    }

}