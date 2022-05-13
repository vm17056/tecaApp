package com.sv.proye.tecaapp.views.ui.colecciones;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.ColeccionDao;
import com.sv.proye.tecaapp.models.Coleccion;
import com.sv.proye.tecaapp.utils.MessageUtils;

public class ColeccionFragment extends Fragment {

    private ColeccionViewModel mViewModel;
    private Coleccion coleccionSelected;
    private TextView inputCodigo;
    private TextView inputNombre;
    private MaterialButton btnSave;
    private ColeccionDao coleccionDao;

    public ColeccionFragment() {
    }

    public ColeccionFragment(Coleccion coleccionSelected) {
        this.coleccionSelected = coleccionSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.coleccion_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ColeccionViewModel.class);
        inputCodigo = view.findViewById(R.id.coleccion_codigo);
        inputNombre = view.findViewById(R.id.coleccion_nombre);
        btnSave = view.findViewById(R.id.coleccion_savebtn);
        coleccionDao = new ColeccionDao(ColeccionFragment.this.requireActivity());
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarColeccio();
            }
        });
        colocarDatos(coleccionSelected);
        return view;
    }

    private void colocarDatos(Coleccion o) {
        if (o != null) {
            inputCodigo.setText(o.getCodigo());
            inputNombre.setText(o.getNombre());
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

    private Coleccion recolectarDatos() {
        Coleccion coleccion = new Coleccion();
        if (coleccionSelected != null) coleccion = coleccionSelected;
        coleccion.setNombre(inputNombre.getText().toString());
        coleccion.setCodigo(inputCodigo.getText().toString());
        return coleccion;
    }

    private void guardarColeccio() {
        if (validarDatos()) {
            Long registrado = -1L;
            if (coleccionSelected != null){
                registrado = (long) coleccionDao.actualizarModelo(recolectarDatos());
                coleccionSelected = null;
            }else{
                registrado = coleccionDao.almacenarModelo(recolectarDatos());
            }
            if (registrado == -1 || registrado == 0)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), ColeccionFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Coleccion registrada", ColeccionFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), ColeccionFragment.this.requireActivity());
        }
    }
}