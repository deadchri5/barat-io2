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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class InventarioListAdapter extends ListAdapter<Producto, InventarioListAdapter.InventarioViewHolder> {

    protected InventarioListAdapter() {
        super(Producto.itemCallback);
    }

    public class InventarioViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        InventarioListAdapter inventarioListAdapter;
        Context context;
        InventarioRowBinding inventarioRowBinding;
        ImageButton btnDelete, btnModificar;
        SharedPreferences sharedPreferences;

        public InventarioViewHolder(InventarioRowBinding binding,
                                    InventarioListAdapter inventarioListAdapter) {
            super(binding.getRoot());
            context = binding.getRoot().getContext();
            inventarioRowBinding = binding;
            btnDelete = binding.getRoot().findViewById(R.id.btnEliminar);
            btnModificar = binding.getRoot().findViewById(R.id.btnModificar);
            this.inventarioListAdapter = inventarioListAdapter;
        }

        public void setOnClickListeners(Producto producto) {
            inventarioRowBinding.setProducto(producto);
            btnDelete.setOnClickListener(this);
            btnModificar.setOnClickListener(view -> {
                final ModificarProductoDialogFragment modificarDialog =
                        new ModificarProductoDialogFragment(this, producto);
                //fbDialogue.getWindow().setBackgroundDrawable(new ColorDrawable(Color.argb(100, 0, 0, 0)));
                //crearDialog.setContentView(R.layout.fragment_crear_producto);
                modificarDialog.setCancelable(true);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                modificarDialog.show(activity.getSupportFragmentManager(), "dialog");
            });
        }

        @Override
        public void onClick(View view) {
            Gson gson = new Gson();
            ArrayList<Producto> productoList;
            Producto producto = inventarioRowBinding.getProducto();
            sharedPreferences = context.getSharedPreferences("MySharedPref", MODE_PRIVATE);
            if (view.getId() == R.id.btnEliminar) {
                if (sharedPreferences.contains("productoList")) {
                    int position = 0;
                    String productID = producto.getId();
                    String json = sharedPreferences.getString("productoList", "");
                    productoList = gson.fromJson(json, new TypeToken<ArrayList<Producto>>() {
                    }.getType());
                    for (int i = 0; i < productoList.size(); i++) {
                        if (productoList.get(i).getId().equals(productID)) {
                            position = i;
                            Toast.makeText(context, "Producto " + productoList.get(i).getNombre() + " Eliminado", Toast.LENGTH_SHORT).show();
                            productoList.remove(i);
                        }
                    }
                    String newJson = gson.toJson(productoList);
                    SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
                    prefsEditor.putString("productoList", newJson);
                    prefsEditor.commit();
                    submitList(productoList);
                    notifyItemRemoved(position);
                } else {
                    Toast.makeText(context, "Hay productos que eliminar", Toast.LENGTH_SHORT).show();
                }
            }
        }

    }

    @NonNull
    @Override
    public InventarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        InventarioRowBinding inventarioRowBinding = InventarioRowBinding.inflate(layoutInflater,
                parent, false);
        return new InventarioViewHolder(inventarioRowBinding, this);
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioViewHolder holder, int position) {
        Producto producto = getItem(position);
        holder.setOnClickListeners(producto);
    }

}