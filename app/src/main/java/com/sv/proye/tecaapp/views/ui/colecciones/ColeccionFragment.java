package com.sv.proye.tecaapp.views.ui.colecciones;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sv.proye.tecaapp.R;

public class ColeccionFragment extends Fragment {

    private ColeccionViewModel mViewModel;

    public static ColeccionFragment newInstance() {
        return new ColeccionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(ColeccionViewModel.class);
        return inflater.inflate(R.layout.coleccion_fragment, container, false);
    }


}