package com.sv.proye.tecaapp.views.ui.libros;

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

public class LibrosFragment extends Fragment {
    private LibrosViewModel mViewModel;

    AppCompatSpinner spinnerAutores;
    EditText inputCodigo;
    EditText inputNombre;
    DatePicker inputFechaPublicacion;
    MaterialButton btnSave;

    public static LibrosFragment newInstance() {
        return new LibrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(LibrosViewModel.class);
        View view = inflater.inflate(R.layout.libros_fragment, container, false);
        spinnerAutores = view.findViewById(R.id.book_autor);
        inputNombre = view.findViewById(R.id.book_nombre);
        inputCodigo = view.findViewById(R.id.book_codigo);
        inputFechaPublicacion = view.findViewById(R.id.book_fechapublicacion);
        btnSave = view.findViewById(R.id.book_savebtn);
        return view;
    }

}