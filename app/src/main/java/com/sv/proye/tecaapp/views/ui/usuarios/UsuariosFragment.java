package com.sv.proye.tecaapp.views.ui.usuarios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.UsuarioDao;
import com.sv.proye.tecaapp.models.Usuario;
import com.sv.proye.tecaapp.views.adapters.editablas.UsuariosEditableRecyclerAdapter;

import java.util.List;

public class UsuariosFragment extends Fragment {

    private UsuariosViewModel mViewModel;
    private RecyclerView recyclerUsuarios;

    public static UsuariosFragment newInstance() {
        return new UsuariosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.usuarios_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(UsuariosViewModel.class);
        recyclerUsuarios = view.findViewById(R.id.usuarios_recyclerView);
        cargarUsuarios();
        return view;
    }

    private void cargarUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDao(UsuariosFragment.this.requireActivity());
        List<Usuario> usuarios = usuarioDao.listarModelos();
        UsuariosEditableRecyclerAdapter adapter = new UsuariosEditableRecyclerAdapter(usuarios, UsuariosFragment.this.requireActivity(), getParentFragmentManager());
        recyclerUsuarios.setLayoutManager(new LinearLayoutManager(UsuariosFragment.this.requireActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerUsuarios.setAdapter(adapter);
    }

}