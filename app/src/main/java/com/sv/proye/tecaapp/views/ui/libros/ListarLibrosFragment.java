package com.sv.proye.tecaapp.views.ui.libros;

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
import com.sv.proye.tecaapp.dto.CompraDao;
import com.sv.proye.tecaapp.dto.FavoritoDao;
import com.sv.proye.tecaapp.dto.HistorialLeidoDao;
import com.sv.proye.tecaapp.dto.InventarioDao;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.views.adapters.ListaLibrosReciclerAdapter;

import java.util.List;

public class ListarLibrosFragment extends Fragment {

    private ListarLibrosViewModel mViewModel;
    private  RecyclerView recyclerLibros;

    public static ListarLibrosFragment newInstance() {
        return new ListarLibrosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listar_libros_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ListarLibrosViewModel.class);
        recyclerLibros = view.findViewById(R.id.listar_libros_recicler);
        HistorialLeidoDao historialLeidoDao = new HistorialLeidoDao(this.requireContext());
        FavoritoDao favoritoDao = new FavoritoDao(this.requireContext());
        InventarioDao inventarioDao = new InventarioDao(this.requireContext());
        List<Inventario> inventarioList = inventarioDao.listarModelos();
        ListaLibrosReciclerAdapter listaLibrosReciclerAdapter = new ListaLibrosReciclerAdapter(inventarioList,historialLeidoDao,favoritoDao);
        recyclerLibros.setLayoutManager(new LinearLayoutManager(this.requireContext(), LinearLayoutManager.VERTICAL, false));
        recyclerLibros.setAdapter(listaLibrosReciclerAdapter);
        return view;
    }

}