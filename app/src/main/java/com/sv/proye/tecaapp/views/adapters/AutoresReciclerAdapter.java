package com.sv.proye.tecaapp.views.adapters;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.sv.proye.tecaapp.models.Autor;

import java.util.List;

public class AutoresReciclerAdapter extends AbsTextOnlyReciclerAdapter<Autor> {


    public AutoresReciclerAdapter(List<Autor> items, Context context, FragmentActivity fragmentActivity) {
        super(items, context, fragmentActivity);
    }

    @Override
    public void asignarDatillos(Autor data) {
        materialTextView.setText(data.getNombre());
    }
}
