package com.sv.proye.tecaapp.views.adapters.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.views.adapters.AbsSpinnerAdapter;
import com.sv.proye.tecaapp.views.adapters.holders.TextOnlyHolder;

import java.util.List;

public class LibrosSpinnerAdapter extends AbsSpinnerAdapter<Libro> {

    public static Libro libroSeleccionado;
    private LayoutInflater layoutInflater;
    private Context context;

    public LibrosSpinnerAdapter(List<Libro> lista, Context context) {
        super(lista);
        this.context = context;
        LibrosSpinnerAdapter.libroSeleccionado = null;
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

            final Libro libro = getItem(position);

            String nombreLibro = libro.getCodigo() + " " + libro.getNombre();
            holder.textView.setText(nombreLibro);
            holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LibrosSpinnerAdapter.libroSeleccionado = libro;
                }
            });

        } catch (Exception e) {
            System.out.println("Error LibrosSpinnerAdapter" + e);
        }
        return convertView;
    }

}
