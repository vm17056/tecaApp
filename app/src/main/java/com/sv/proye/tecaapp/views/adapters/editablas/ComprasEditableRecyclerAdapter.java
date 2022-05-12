package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.sv.proye.tecaapp.models.Compra;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.inventario.ComprasFragment;

import java.text.SimpleDateFormat;
import java.util.List;

public class ComprasEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<Compra> {

    //    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentManager fragmentActivity;

    public ComprasEditableRecyclerAdapter(List<Compra> lista, Context context, FragmentManager fragmentActivity) {
        super(lista, context, fragmentActivity);
//        this.context = context;
        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void asignarDatillos(Compra data) {
        if (viewHolder != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
            String nombre = data.getInventario().getLibro().getNombre() + " - " + simpleDateFormat.format(data.getFechaCompra());
            viewHolder.textView.setText(nombre);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentActivity, new ComprasFragment(data));
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
//            final Compra compra = getItem(position);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
//            String nombre = compra.getInventario().getLibro().getNombre() + " - " + simpleDateFormat.format(compra.getFechaCompra());
//            holder.textView.setText(nombre);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new ComprasFragment(compra));
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
