package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.libros.LibrosFragment;

import java.util.List;

public class LibrosEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<Libro> {

    //    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentActivity fragmentActivity;

    public LibrosEditableRecyclerAdapter(List<Libro> lista, Context context, FragmentActivity fragmentActivity) {
        super(lista, context, fragmentActivity);
//        this.context = context;
//        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void asignarDatillos(Libro data) {
        if (viewHolder != null) {
            String nombre = data.getNombre() + " - " + data.getCodigo() + " # " + data.getAutor().getNombre();
            viewHolder.textView.setText(nombre);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentActivity, new LibrosFragment(data));
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
//            final Libro libro = getItem(position);
//            String nombre = libro.getNombre() + " - " + libro.getCodigo() + " # " + libro.getAutor().getNombre();
//            holder.textView.setText(nombre);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new LibrosFragment(libro));
//                }
//            });
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
