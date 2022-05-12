package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.inventario.InventarioFragment;

import java.text.SimpleDateFormat;
import java.util.List;

public class InventariosEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<Inventario> {

    //    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentManager fragmentActivity;

    public InventariosEditableRecyclerAdapter(List<Inventario> lista, Context context, FragmentManager fragmentActivity) {
        super(lista, context, fragmentActivity);
//        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void asignarDatillos(Inventario data) {
        if (viewHolder != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
            String nombre = data.getLibro().getNombre() + " - " + simpleDateFormat.format(data.getFechaActualizado());
            viewHolder.textView.setText(nombre);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentActivity, new InventarioFragment(data));
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
//            final Inventario inventario = getItem(position);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
//            String nombre = inventario.getLibro().getNombre() + " - " + simpleDateFormat.format(inventario.getFechaActualizado());
//            holder.textView.setText(nombre);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new InventarioFragment(inventario));
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
