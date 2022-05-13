package com.sv.proye.tecaapp.views.ui.libros;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.databinding.LibroDeseadoFragmentBinding;
import com.sv.proye.tecaapp.dto.AutorDao;
import com.sv.proye.tecaapp.dto.LibroDao;
import com.sv.proye.tecaapp.models.Autor;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.adapters.spinners.AutoresSpinnerAdapter;

import java.util.Date;
import java.util.List;

public class LibrosFragment extends Fragment {

    private LibrosViewModel mViewModel;
    private LibroDeseadoFragmentBinding binding;
    private AppCompatSpinner spinnerAutores;
    private Libro libroSelected;
    private EditText inputCodigo;
    private EditText inputNombre;
    private DatePicker inputFechaPublicacion;
    private MaterialButton btnSave;
    private LibroDao libroDao;
    private Long maxDate = DateUtils.getActualDate().getTime();

    public LibrosFragment() {
    }

    public LibrosFragment(Libro libroSelected) {
        this.libroSelected = libroSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.libros_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(LibrosViewModel.class);
        spinnerAutores = view.findViewById(R.id.book_autor);
        inputNombre = view.findViewById(R.id.book_nombre);
        inputCodigo = view.findViewById(R.id.book_codigo);
        inputFechaPublicacion = view.findViewById(R.id.book_fechapublicacion);
        btnSave = view.findViewById(R.id.book_savebtn);
        libroDao = new LibroDao(LibrosFragment.this.requireActivity());
        inputFechaPublicacion.setMaxDate(maxDate);
        cargarAutores();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLibro();
            }
        });
        cargarDatos(libroSelected);
        return view;
    }

    private void cargarDatos(Libro o) {
        if (o != null) {
            inputCodigo.setText(o.getCodigo());
            inputNombre.setText(o.getNombre());
            DateUtils.setDateToPicker(inputFechaPublicacion, o.getFechaPublicacion());
        }
    }

    private void guardarLibro() {
        if (validarDatos()) {
            Long registrado = -1L;
            if (libroSelected != null) {
                registrado = (long) libroDao.actualizarModelo(recolectarDatos());
                libroSelected = null;
            } else {
                registrado = libroDao.almacenarModelo(recolectarDatos());
            }
            if (registrado == -1 || registrado == 0)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), LibrosFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Libro Registrado", LibrosFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), LibrosFragment.this.requireActivity());
        }
    }

    private void cargarAutores() {
        AutorDao autorDao = new AutorDao(LibrosFragment.this.requireActivity());
        List<Autor> autores = autorDao.listarModelos();
        AutoresSpinnerAdapter autoresSpinnerAdapter = new AutoresSpinnerAdapter(autores, LibrosFragment.this.requireActivity());
        spinnerAutores.setAdapter(autoresSpinnerAdapter);
        if (libroSelected != null) {
            int pos = autores.indexOf(libroSelected.getAutor());
            spinnerAutores.setSelection(pos);
        }
        ;
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (AutoresSpinnerAdapter.autorSeleccionado == null) {
            spinnerAutores.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerAutores.setBackgroundColor(Color.TRANSPARENT);
        }
        if (inputCodigo.getText().toString().isEmpty()) {
            inputCodigo.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputNombre.getText().toString().isEmpty()) {
            inputNombre.setHintTextColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Libro recolectarDatos() {
        Libro libro = new Libro();
        if (libroSelected != null) libro = libroSelected;
        libro.setAutor(AutoresSpinnerAdapter.autorSeleccionado);
        libro.setCodigo(inputCodigo.getText().toString());
        libro.setNombre(inputNombre.getText().toString());
        libro.setFechaPublicacion(new Date(inputFechaPublicacion.getCalendarView().getDate()));
        return libro;
    }

}