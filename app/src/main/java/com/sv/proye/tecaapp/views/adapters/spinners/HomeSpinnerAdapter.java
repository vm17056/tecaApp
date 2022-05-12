package com.sv.proye.tecaapp.views.adapters.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.views.adapters.AbsSpinnerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.AutoresEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ColeccionEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ColeccionLibroEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ComprasEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.InventariosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.LibroDeseadoEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.LibrosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.PrestamosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.holders.TextOnlyHolder;

import java.util.List;

public class HomeSpinnerAdapter extends AbsSpinnerAdapter<String> {

    private LayoutInflater layoutInflater;
    private Context context;

    RecyclerView recyclerView;
    AutoresEditableRecyclerAdapter autoresEditableRecyclerAdapter;
    ColeccionEditableRecyclerAdapter coleccionEditableRecyclerAdapter;
    ColeccionLibroEditableRecyclerAdapter coleccionLibroEditableRecyclerAdapter;
    ComprasEditableRecyclerAdapter comprasEditableRecyclerAdapter;
    InventariosEditableRecyclerAdapter inventariosEditableRecyclerAdapter;
    LibroDeseadoEditableRecyclerAdapter libroDeseadoEditableRecyclerAdapter;
    LibrosEditableRecyclerAdapter librosEditableRecyclerAdapter;
    PrestamosEditableRecyclerAdapter prestamosEditableRecyclerAdapte;

    public HomeSpinnerAdapter(List<String> lista, Context context, RecyclerView recyclerView, AutoresEditableRecyclerAdapter autoresEditableRecyclerAdapter, ColeccionEditableRecyclerAdapter coleccionEditableRecyclerAdapter, ColeccionLibroEditableRecyclerAdapter coleccionLibroEditableRecyclerAdapter, ComprasEditableRecyclerAdapter comprasEditableRecyclerAdapter, InventariosEditableRecyclerAdapter inventariosEditableRecyclerAdapter, LibroDeseadoEditableRecyclerAdapter libroDeseadoEditableRecyclerAdapter, LibrosEditableRecyclerAdapter librosEditableRecyclerAdapter, PrestamosEditableRecyclerAdapter prestamosEditableRecyclerAdapte) {
        super(lista);
        this.context = context;
        this.recyclerView = recyclerView;
        this.autoresEditableRecyclerAdapter = autoresEditableRecyclerAdapter;
        this.coleccionEditableRecyclerAdapter = coleccionEditableRecyclerAdapter;
        this.coleccionLibroEditableRecyclerAdapter = coleccionLibroEditableRecyclerAdapter;
        this.comprasEditableRecyclerAdapter = comprasEditableRecyclerAdapter;
        this.inventariosEditableRecyclerAdapter = inventariosEditableRecyclerAdapter;
        this.libroDeseadoEditableRecyclerAdapter = libroDeseadoEditableRecyclerAdapter;
        this.librosEditableRecyclerAdapter = librosEditableRecyclerAdapter;
        this.prestamosEditableRecyclerAdapte = prestamosEditableRecyclerAdapte;
    }

    @Override
    public View absView(int position, View convertView, ViewGroup parent) {
        TextOnlyHolder holder;

        try {
            if (convertView == null) {
                convertView = layoutInflater.from(context).inflate(R.layout.holder_item_text_only, parent, false);
                holder = new TextOnlyHolder(convertView) {
                    @Override
                    public void asignarDatos(Object data) {
                        textView.setText("asd");
                    }
                };
                convertView.setTag(holder);
                System.out.println("Holder Activo");
            } else {
                holder = (TextOnlyHolder) convertView.getTag();
                System.out.println("Holder Inactivo");
            }

            final String texto = getItem(position);

            holder.textView.setText(texto);
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                    switch (texto) {
                        case "Autores":
                            recyclerView.setAdapter(autoresEditableRecyclerAdapter);
                            break;
                        case "Colecciones":
                            recyclerView.setAdapter(coleccionEditableRecyclerAdapter);
                            break;
                        case "Colecciones de libro":
                            recyclerView.setAdapter(coleccionLibroEditableRecyclerAdapter);
                            break;
                        case "Compras":
                            recyclerView.setAdapter(comprasEditableRecyclerAdapter);
                            break;
                        case "Inventarios":
                            recyclerView.setAdapter(inventariosEditableRecyclerAdapter);
                            break;
                        case "Libros deseados":
                            recyclerView.setAdapter(libroDeseadoEditableRecyclerAdapter);
                            break;
                        case "Libros":
                            recyclerView.setAdapter(librosEditableRecyclerAdapter);
                            break;
                        case "Prestamos":
                            recyclerView.setAdapter(prestamosEditableRecyclerAdapte);
                            break;
                    }

                }
            });

        } catch (Exception e) {
            System.out.println("Error UsuarioSpinnerAdapter" + e);
        }
        return convertView;
    }

}
