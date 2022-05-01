package com.sv.proye.tecaapp.views.ui.colecciones;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.ColeccionDao;
import com.sv.proye.tecaapp.dto.ColeccionLibroDao;
import com.sv.proye.tecaapp.dto.LibroDao;
import com.sv.proye.tecaapp.models.Coleccion;
import com.sv.proye.tecaapp.models.ColeccionLibro;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.adapters.spinners.ColeccionSpinnerAdapter;
import com.sv.proye.tecaapp.views.adapters.spinners.LibrosSpinnerAdapter;

import java.util.List;

public class ColeccionLibroFragment extends Fragment {

    private ColeccionLibroViewModel mViewModel;
    private ColeccionLibro coleccionSelected;
    private AppCompatSpinner spinnerLibro;
    private AppCompatSpinner spinnerColeccion;
    private MaterialButton btnSave;
    private ColeccionLibroDao coleccionLibroDao;

    public ColeccionLibroFragment() {
    }

    public ColeccionLibroFragment(ColeccionLibro coleccionSelected) {
        this.coleccionSelected = coleccionSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coleccion_libro_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ColeccionLibroViewModel.class);
        spinnerLibro = view.findViewById(R.id.collecionlibro_libro);
        spinnerColeccion = view.findViewById(R.id.collecionlibro_colecion);
        btnSave = view.findViewById(R.id.coleccionlibro_savebtn);
        coleccionLibroDao = new ColeccionLibroDao(ColeccionLibroFragment.this.requireActivity());
        cargarLibros();
        cargarColecciones();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarColeccionLibro();
            }
        });
        return view;
    }

    private void guardarColeccionLibro() {
        if (validarDatos()) {
            Long registrado = coleccionLibroDao.almacenarModelo(recolectarDatos());
            if (registrado == -1)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), ColeccionLibroFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Coleccion de libro registrada", ColeccionLibroFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), ColeccionLibroFragment.this.requireActivity());
        }
    }

    private void cargarLibros() {
        LibroDao autorDao = new LibroDao(ColeccionLibroFragment.this.requireActivity());
        List<Libro> autores = autorDao.listarModelos();
        LibrosSpinnerAdapter librosSpinnerAdapter = new LibrosSpinnerAdapter(autores, ColeccionLibroFragment.this.requireActivity());
        spinnerLibro.setAdapter(librosSpinnerAdapter);
        if (coleccionSelected != null) {
            int pos = autores.indexOf(coleccionSelected.getLibro());
            spinnerLibro.setSelection(pos);
        }
        ;
    }

    private void cargarColecciones() {
        ColeccionDao autorDao = new ColeccionDao(ColeccionLibroFragment.this.requireActivity());
        List<Coleccion> autores = autorDao.listarModelos();
        ColeccionSpinnerAdapter coleccionesSpinnerAdapter = new ColeccionSpinnerAdapter(autores, ColeccionLibroFragment.this.requireActivity());
        spinnerColeccion.setAdapter(coleccionesSpinnerAdapter);
        if (coleccionSelected != null) {
            int pos = autores.indexOf(coleccionSelected.getColeccion());
            spinnerColeccion.setSelection(pos);
        }
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (LibrosSpinnerAdapter.libroSeleccionado == null) {
            spinnerLibro.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerLibro.setBackgroundColor(Color.TRANSPARENT);
        }
        if (ColeccionSpinnerAdapter.coleccionSeleccionada == null) {
            spinnerColeccion.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerColeccion.setBackgroundColor(Color.TRANSPARENT);
        }
        return valid;
    }

    private ColeccionLibro recolectarDatos() {
        ColeccionLibro libro = new ColeccionLibro();
        if (coleccionSelected != null) libro = coleccionSelected;
        libro.setLibro(LibrosSpinnerAdapter.libroSeleccionado);
        libro.setColeccion(ColeccionSpinnerAdapter.coleccionSeleccionada);
        return libro;
    }

}