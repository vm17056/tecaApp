package com.sv.proye.tecaapp.views.ui.autores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.models.Autor;

public class AutoresFragment extends Fragment {

    private AutoresViewModel mViewModel;
    private Autor autorSelected;

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
        return view;
    }


}