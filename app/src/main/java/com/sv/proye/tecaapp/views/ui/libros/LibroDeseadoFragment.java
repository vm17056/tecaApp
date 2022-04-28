package com.sv.proye.tecaapp.views.ui.libros;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sv.proye.tecaapp.R;

public class LibroDeseadoFragment extends Fragment {

    private LibroDeseadoViewModel mViewModel;

    public static LibroDeseadoFragment newInstance() {
        return new LibroDeseadoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(LibroDeseadoViewModel.class);
        return inflater.inflate(R.layout.libro_deseado_fragment, container, false);
    }

}