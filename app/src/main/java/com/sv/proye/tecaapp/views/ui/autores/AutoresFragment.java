package com.sv.proye.tecaapp.views.ui.autores;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.AutorDao;
import com.sv.proye.tecaapp.models.Autor;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;

import java.util.Date;

public class AutoresFragment extends Fragment {

    private AutoresViewModel mViewModel;
    private Autor autorSelected;
    private EditText inputNombre;
    private EditText inputApellido;
    private DatePicker inputFechaNacimiento;
    private ToggleButton inputFallecido;
    private DatePicker inputDefuncion;
    private MaterialButton btnGuardar;
    private Long maxDate = DateUtils.getActualDate().getTime();
    private AutorDao autorDao;

    public AutoresFragment() {
    }

    public AutoresFragment(Autor autorSelected) {
        this.autorSelected = autorSelected;
    }

    public static AutoresFragment newInstance(Autor autorSelected) {
        return new AutoresFragment(autorSelected);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.autores_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(AutoresViewModel.class);
        autorDao = new AutorDao(this.requireActivity());
        inputNombre = view.findViewById(R.id.autor_name);
        inputApellido = view.findViewById(R.id.autor_lastname);
        inputFechaNacimiento = view.findViewById(R.id.autor_fechanacimiento);
        inputFallecido = view.findViewById(R.id.autor_fallecido);
        inputDefuncion = view.findViewById(R.id.autor_fechadefuncion);
        btnGuardar = view.findViewById(R.id.autor_savebtn);

        inputFechaNacimiento.setMaxDate(maxDate);
        inputDefuncion.setMaxDate(maxDate);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAutor();
            }
        });
        return view;
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (inputNombre.getText().toString().isEmpty()) {
            inputNombre.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputApellido.getText().toString().isEmpty()) {
            inputApellido.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputFechaNacimiento.getCalendarView().getDate() == DateUtils.getActualDate().getTime()) {
            inputFechaNacimiento.setBackgroundColor(Color.RED);
            valid = false;
        }
        if (inputFallecido.getText().toString().isEmpty()) {
            inputFallecido.setHintTextColor(Color.RED);
            valid = false;
        }
        if (!inputFallecido.getText().toString().isEmpty()
                && getResources().getString(R.string.yes).equalsIgnoreCase(inputFallecido.getText().toString())
                && inputFechaNacimiento.getCalendarView().getDate() >= inputDefuncion.getCalendarView().getDate()) {
            inputFechaNacimiento.setBackgroundColor(Color.YELLOW);
            inputDefuncion.setBackgroundColor(Color.YELLOW);
            valid = false;
        } else {
            inputDefuncion.setBackgroundColor(Color.TRANSPARENT);
        }
        if (!inputFallecido.getText().toString().isEmpty()
                && getResources().getString(R.string.yes).equalsIgnoreCase(inputFallecido.getText().toString())
                && inputDefuncion.getCalendarView().getDate() == DateUtils.getActualDate().getTime()) {
            inputDefuncion.setBackgroundColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Autor recolectarDatos() {
        Autor autor = new Autor();
        autor.setNombre(inputNombre.getText().toString());
        autor.setApellido(inputApellido.getText().toString());
        autor.setFechaNacimiento(new Date(inputFechaNacimiento.getCalendarView().getDate()));
        autor.setFallecido(getResources().getString(R.string.yes).equalsIgnoreCase(inputFallecido.getText().toString()));
        if (autor.getFallecido()) {
            autor.setFechaDefuncion(new Date(inputDefuncion.getCalendarView().getDate()));
        } else {
            autor.setFechaDefuncion(new Date());
        }
        return autor;
    }

    private void guardarAutor() {
        if (validarDatos()) {
            Long registrado = autorDao.almacenarModelo(recolectarDatos());
            if (registrado == -1)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), AutoresFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Autor Registrado", AutoresFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), AutoresFragment.this.requireActivity());
        }
    }


}