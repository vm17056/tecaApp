package com.sv.proye.tecaapp.views.adapters.editablas;

import android.content.Context;
import android.view.View;

import androidx.fragment.app.FragmentActivity;

import com.sv.proye.tecaapp.models.Prestamo;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.FragmentUtils;
import com.sv.proye.tecaapp.views.adapters.AbsTextBtnEditReciclerAdapter;
import com.sv.proye.tecaapp.views.ui.inventario.PrestamosFragment;

import java.text.SimpleDateFormat;
import java.util.List;

public class PrestamosEditableRecyclerAdapter extends AbsTextBtnEditReciclerAdapter<Prestamo> {

    //    private LayoutInflater layoutInflater;
//    private Context context;
    private FragmentActivity fragmentActivity;

    public PrestamosEditableRecyclerAdapter(List<Prestamo> lista, Context context, FragmentActivity fragmentActivity) {
        super(lista, context, fragmentActivity);
//        this.context = context;
//        this.fragmentActivity = fragmentActivity;
    }

    @Override
    public void asignarDatillos(Prestamo data) {
        if (viewHolder != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
            String nombre = data.getUsuario().getNombre() + " # " + simpleDateFormat.format(data.getFechaPrestamo()) + " # " + simpleDateFormat.format(data.getFechaEntrega());
            viewHolder.textView.setText(nombre);
            viewHolder.editBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentUtils.showFragmentGlobal(fragmentActivity, new PrestamosFragment(data));
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
//            final Prestamo prestamo = getItem(position);
//            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DateUtils.FORMAT_YYYY_MM_DD);
//            String nombre = prestamo.getUsuario().getNombre() + " # " + simpleDateFormat.format(prestamo.getFechaPrestamo()) + " # " + simpleDateFormat.format(prestamo.getFechaEntrega());
//            holder.textView.setText(nombre);
//            holder.editBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    FragmentUtils.showFragmentGlobal(fragmentActivity, new PrestamosFragment(prestamo));
//                }
//            });
//
//        } catch (Exception e) {
//            System.out.println("Error AutoresSpinnerAdapter" + e);
//        }
//        return convertView;
//    }

}
