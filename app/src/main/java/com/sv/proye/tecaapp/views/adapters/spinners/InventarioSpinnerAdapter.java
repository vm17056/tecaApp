package com.sv.proye.tecaapp.views.adapters.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.views.adapters.AbsSpinnerAdapter;
import com.sv.proye.tecaapp.views.adapters.TextOnlyHolder;

import java.util.List;

public class InventarioSpinnerAdapter extends AbsSpinnerAdapter<Inventario> {

    public static Inventario inventarioSeleccionado;
    private LayoutInflater layoutInflater;
    private Context context;

    public InventarioSpinnerAdapter(List<Inventario> lista, Context context) {
        super(lista);
        this.context = context;
        InventarioSpinnerAdapter.inventarioSeleccionado = null;
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

            final Inventario inventario = getItem(position);

            String nombreInventario = inventario.getLibro().getNombre() + " " + inventario.getCantidad();
            holder.textView.setText(nombreInventario);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InventarioSpinnerAdapter.inventarioSeleccionado = inventario;
                }
            });

        } catch (Exception e) {
            System.out.println("Error InventarioSpinnerAdapter" + e);
        }
        return convertView;
    }

}
