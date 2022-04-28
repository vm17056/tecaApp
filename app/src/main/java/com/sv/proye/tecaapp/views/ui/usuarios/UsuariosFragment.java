package com.sv.proye.tecaapp.views.ui.usuarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.sv.proye.tecaapp.R;

public class UsuariosFragment extends Fragment {

    private UsuariosViewModel mViewModel;

    public static UsuariosFragment newInstance() {
        return new UsuariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuarios_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(UsuariosViewModel.class);
        return view;
    }

}