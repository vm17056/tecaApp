package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.sv.proye.tecaapp.models.ColeccionLibro;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.colecciones.ColeccionLibroFragment;

import java.util.List;

public class ColeccionLibroEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<ColeccionLibro> {

//    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentActivity fragmentActivity;

    public ColeccionLibroEditableRecyclerAdapter(List<ColeccionLibro> lista, Context context, FragmentActivity fragmentActivity) {
        super(lista, context, fragmentActivity);
//        this.context = context;
//        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void asignarDatillos(ColeccionLibro data) {
        if (viewHolder != null) {
            String nombre = data.getLibro().getNombre() + " - " + data.getColeccion().getNombre();
            viewHolder.textView.setText(nombre);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentActivity, new ColeccionLibroFragment(data));
                }
            });
        }
    }

//    @Override
//    public View absView(int position, View convertView, ViewGroup parent) {
//        TextBtnEditHolder holder;
//
//        try {
//            if (convertView == null) {
//                convertView = layoutInflater.from(context).inflate(R.layout.holder_item_text_btn_edit, parent, false);
//                holder = new TextBtnEditHolder(convertView) {
//                    @Override
//                    public void asignarDatos(Object data) {
//                        textView.setText("asd");
//                    }
//                };
//                convertView.setTag(holder);
//                System.out.println("Holder Activo");
//            } else {
//                holder = (TextBtnEditHolder) convertView.getTag();
//                System.out.println("Holder Inactivo");
//            }
//
//            final ColeccionLibro coleccion = getItem(position);
//
//            String nombre = coleccion.getLibro().getNombre() + " - " + coleccion.getColeccion().getNombre();
//            holder.textView.setText(nombre);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new ColeccionLibroFragment(coleccion));
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
