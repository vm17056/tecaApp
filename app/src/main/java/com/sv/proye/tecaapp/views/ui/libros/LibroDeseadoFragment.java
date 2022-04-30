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
    private EditText inputCodigo;
    private EditText inputNombre;
    private MaterialButton btnSave;
    private LibroDeseadoDao libroDeseadoDao;

    public static LibroDeseadoFragment newInstance() {
        return new LibroDeseadoFragment();
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
        return view;
    }

    private void guardarLibro() {
        if (validarDatos()) {
            Usuario registrar = recolectarDatos().getUsuario();
            if (registrar != null) {
                Long registrado = libroDeseadoDao.almacenarModelo(recolectarDatos());
                if (registrado == -1)
                    MessageUtils.displayFail(getResources().getString(R.string.database_error), LibroDeseadoFragment.this.requireActivity());
                else
                    MessageUtils.displaySuccess("Libro Deseado Registrado", LibroDeseadoFragment.this.requireActivity());
            } else {
                MessageUtils.displaySuccess("Libro Deseado Registrado", LibroDeseadoFragment.this.requireActivity());
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
        libro.setCodigo(inputCodigo.getText().toString());
        libro.setNombre(inputNombre.getText().toString());
        libro.setUsuario(StaticUtils.usuario);
        return libro;
    }
}