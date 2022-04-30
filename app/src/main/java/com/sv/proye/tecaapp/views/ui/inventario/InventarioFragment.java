package com.sv.proye.tecaapp.views.ui.inventario;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.InventarioDao;
import com.sv.proye.tecaapp.dto.LibroDao;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.adapters.spinners.LibrosSpinnerAdapter;

import java.util.Date;
import java.util.List;

public class InventarioFragment extends Fragment {

    private InventarioViewModel mViewModel;
    private AppCompatSpinner spinnerLibros;
    private NumberPicker inputCantidad;
    private DatePicker inputFechaActualizado;
    private MaterialButton saveBtn;
    private Long maxDate = DateUtils.getActualDate().getTime();
    private InventarioDao inventarioDao;

    public static InventarioFragment newInstance() {
        return new InventarioFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventario_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(InventarioViewModel.class);
        spinnerLibros = view.findViewById(R.id.inventario_libro);
        inputCantidad = view.findViewById(R.id.inventario_cantidad);
        inputFechaActualizado = view.findViewById(R.id.inventario_fechaactualizado);
        saveBtn = view.findViewById(R.id.inventario_savebtn);
        inputFechaActualizado.setMaxDate(maxDate);
        inputCantidad.setMinValue(0);
        inputCantidad.setWrapSelectorWheel(true);
        inventarioDao = new InventarioDao(InventarioFragment.this.requireActivity());
        cargarLibros();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarInventario();
            }
        });
        return view;
    }

    private void cargarLibros() {
        LibroDao libroDao = new LibroDao(InventarioFragment.this.requireActivity());
        List<Libro> libros = libroDao.listarModelos();
        LibrosSpinnerAdapter spinnerAdapter = new LibrosSpinnerAdapter(libros, InventarioFragment.this.requireActivity());
        spinnerLibros.setAdapter(spinnerAdapter);
    }

    private void guardarInventario() {
        if (validarDatos()) {
            Long registrado = inventarioDao.almacenarModelo(recolectarDatos());
            if (registrado == -1)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), InventarioFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Inventario Registrado", InventarioFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), InventarioFragment.this.requireActivity());
        }
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (LibrosSpinnerAdapter.libroSeleccionado == null) {
            spinnerLibros.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerLibros.setBackgroundColor(Color.TRANSPARENT);
        }
        if (inputCantidad.getValue() < 0) {
            inputCantidad.setBackgroundColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Inventario recolectarDatos() {
        Inventario inventario = new Inventario();
        inventario.setLibro(LibrosSpinnerAdapter.libroSeleccionado);
        inventario.setCantidad(inputCantidad.getValue());
        inventario.setFechaActualizado(new Date(inputFechaActualizado.getCalendarView().getDate()));
        return inventario;
    }

}