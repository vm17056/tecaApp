package com.sv.proye.tecaapp.views.adapters.recycler;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;

import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.views.adapters.AbsTextOnlyReciclerAdapter;

import java.util.List;

public class LibrosReciclerAdapterDos extends AbsTextOnlyReciclerAdapter<Libro> {


    public LibrosReciclerAdapterDos(List<Libro> items, Context context, FragmentActivity fragmentActivity) {
        super(items, context, fragmentActivity);
    }

    @Override
    public void asignarDatillos(Libro data) {
        materialTextView.setText(data.getNombre());
    }
}
