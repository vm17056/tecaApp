package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentManager;

import com.sv.proye.tecaapp.models.Autor;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.autores.AutoresFragment;

import java.util.List;

public class AutoresEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<Autor> {

    //    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentManager fragmentManager;

    public AutoresEditableRecyclerAdapter(List<Autor> lista, Context context, FragmentManager fragmentManager) {
        super(lista, context, fragmentManager);
//        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void asignarDatillos(Autor data) {
        if (viewHolder != null) {
            String nombreAutor = data.getNombre() + " " + data.getApellido();
            viewHolder.textView.setText(nombreAutor);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentManager, new AutoresFragment(data));
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
//            final Autor autor = getItem(position);
//
//            String nombreAutor = autor.getNombre() + " " + autor.getApellido();
//            holder.textView.setText(nombreAutor);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new AutoresFragment(autor));
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
