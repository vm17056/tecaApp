package com.sv.proye.tecaapp.views.ui.libros;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.LibroDeseadoDao;
import com.sv.proye.tecaapp.models.LibroDeseado;
import com.sv.proye.tecaapp.models.Usuario;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.utils.StaticUtils;

public class LibroDeseadoFragment extends Fragment {

    private LibroDeseadoViewModel mViewModel;
    private LibroDeseado libroDeseadoSelected;
    private EditText inputCodigo;
    private EditText inputNombre;
    private MaterialButton btnSave;
    private LibroDeseadoDao libroDeseadoDao;

    public LibroDeseadoFragment() {
    }

    public LibroDeseadoFragment(LibroDeseado libroDeseadoSelected) {
        this.libroDeseadoSelected = libroDeseadoSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.libro_deseado_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(LibroDeseadoViewModel.class);
        inputCodigo = view.findViewById(R.id.deseado_codigo);
        inputNombre = view.findViewById(R.id.deseado_nombre);
        btnSave = view.findViewById(R.id.deseado_savebtn);
        libroDeseadoDao = new LibroDeseadoDao(LibroDeseadoFragment.this.requireActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarLibro();
            }
        });
        cargarDatos(libroDeseadoSelected);
        return view;
    }

    private void cargarDatos(LibroDeseado o) {
        if (o != null) {
            inputCodigo.setText(o.getCodigo());
            inputNombre.setText(o.getNombre());
        }
    }

    private void guardarLibro() {
        if (validarDatos()) {
            Usuario usuario = recolectarDatos().getUsuario();
            if (usuario != null) {
                Long registrado = -1L;
                if (libroDeseadoSelected != null) {
                    registrado = (long) libroDeseadoDao.actualizarModelo(recolectarDatos());
                    libroDeseadoSelected = null;
                } else {
                    registrado = libroDeseadoDao.almacenarModelo(recolectarDatos());
                }
                if (registrado == -1 || registrado == 0)
                    MessageUtils.displayFail(getResources().getString(R.string.database_error), LibroDeseadoFragment.this.requireActivity());
                else
                    MessageUtils.displaySuccess("Libro Deseado Registrado", LibroDeseadoFragment.this.requireActivity());
            } else {
                MessageUtils.displaySuccess("Ocurrio un error", LibroDeseadoFragment.this.requireActivity());
            }
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), LibroDeseadoFragment.this.requireActivity());
        }
    }

    private Boolean validarDatos() {
        boolean valid = true;
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

    private LibroDeseado recolectarDatos() {
        LibroDeseado libro = new LibroDeseado();
        if (libroDeseadoSelected != null) libro = libroDeseadoSelected;
        libro.setCodigo(inputCodigo.getText().toString());
        libro.setNombre(inputNombre.getText().toString());
        libro.setUsuario(StaticUtils.usuario);
        if (libroDeseadoSelected != null) libro.setUsuario(libroDeseadoSelected.getUsuario());
        return libro;
    }
}