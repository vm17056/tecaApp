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
    private DatePicker inputFechaDefuncion;
    private MaterialButton btnGuardar;
    private Long maxDate = DateUtils.getActualDate().getTime();
    private AutorDao autorDao;

    public AutoresFragment() {
    }

    public AutoresFragment(Autor autorSelected) {
        this.autorSelected = autorSelected;
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
        inputFechaDefuncion = view.findViewById(R.id.autor_fechadefuncion);
        btnGuardar = view.findViewById(R.id.autor_savebtn);
        inputFechaNacimiento.setMaxDate(maxDate);
        inputFechaDefuncion.setMaxDate(maxDate);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarAutor();
            }
        });
        colocarDatos(autorSelected);
        return view;
    }

    private void colocarDatos(Autor autor) {
        if (autor != null) {
            inputNombre.setText(autor.getNombre());
            inputApellido.setText(autor.getApellido());
            inputFechaNacimiento.getCalendarView().setDate(autor.getFechaNacimiento().getTime());
            inputFechaDefuncion.getCalendarView().setDate(autor.getFechaDefuncion().getTime());
            inputFallecido.setText(autor.getFallecido() ? getResources().getString(R.string.yes) : getResources().getString(R.string.not));
        }
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
                && inputFechaNacimiento.getCalendarView().getDate() >= inputFechaDefuncion.getCalendarView().getDate()) {
            inputFechaNacimiento.setBackgroundColor(Color.YELLOW);
            inputFechaDefuncion.setBackgroundColor(Color.YELLOW);
            valid = false;
        } else {
            inputFechaDefuncion.setBackgroundColor(Color.TRANSPARENT);
        }
        if (!inputFallecido.getText().toString().isEmpty()
                && getResources().getString(R.string.yes).equalsIgnoreCase(inputFallecido.getText().toString())
                && inputFechaDefuncion.getCalendarView().getDate() == DateUtils.getActualDate().getTime()) {
            inputFechaDefuncion.setBackgroundColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Autor recolectarDatos() {
        Autor autor = new Autor();
        if (autorSelected != null) autor = autorSelected;
        autor.setNombre(inputNombre.getText().toString());
        autor.setApellido(inputApellido.getText().toString());
        autor.setFechaNacimiento(new Date(inputFechaNacimiento.getCalendarView().getDate()));
        autor.setFallecido(getResources().getString(R.string.yes).equalsIgnoreCase(inputFallecido.getText().toString()));
        if (autor.getFallecido()) {
            autor.setFechaDefuncion(new Date(inputFechaDefuncion.getCalendarView().getDate()));
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