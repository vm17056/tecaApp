package com.sv.proye.tecaapp.views.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.databinding.FragmentHomeBinding;
import com.sv.proye.tecaapp.dto.AutorDao;
import com.sv.proye.tecaapp.dto.ColeccionDao;
import com.sv.proye.tecaapp.dto.ColeccionLibroDao;
import com.sv.proye.tecaapp.dto.CompraDao;
import com.sv.proye.tecaapp.dto.InventarioDao;
import com.sv.proye.tecaapp.dto.LibroDao;
import com.sv.proye.tecaapp.dto.LibroDeseadoDao;
import com.sv.proye.tecaapp.dto.PrestamoDao;
import com.sv.proye.tecaapp.models.Autor;
import com.sv.proye.tecaapp.models.Coleccion;
import com.sv.proye.tecaapp.models.ColeccionLibro;
import com.sv.proye.tecaapp.models.Compra;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.models.Libro;
import com.sv.proye.tecaapp.models.LibroDeseado;
import com.sv.proye.tecaapp.models.Prestamo;
import com.sv.proye.tecaapp.views.adapters.editablas.AutoresEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ColeccionEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ColeccionLibroEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.ComprasEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.InventariosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.LibroDeseadoEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.LibrosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.editablas.PrestamosEditableRecyclerAdapter;
import com.sv.proye.tecaapp.views.adapters.spinners.HomeSpinnerAdapter;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private AppCompatSpinner spinner;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        HomeViewModel homeViewModel =
//                new ViewModelProvider(this).get(HomeViewModel.class);
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textHome;
        //homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        spinner = root.findViewById(R.id.inicio_spinner);
        recyclerView = root.findViewById(R.id.inicio_recicler);
        cargarSpinner();
        return root;
    }

    private void cargarSpinner() {
        AutorDao autorDao = new AutorDao(HomeFragment.this.requireActivity());
        ColeccionDao coleccionDao = new ColeccionDao(HomeFragment.this.requireActivity());
        ColeccionLibroDao coleccionLibroDao = new ColeccionLibroDao(HomeFragment.this.requireActivity());
        CompraDao compraDao = new CompraDao(HomeFragment.this.requireActivity());
        InventarioDao inventarioDao = new InventarioDao(HomeFragment.this.requireActivity());
        LibroDao libroDao = new LibroDao(HomeFragment.this.requireActivity());
        LibroDeseadoDao libroDeseadoDao = new LibroDeseadoDao(HomeFragment.this.requireActivity());
        PrestamoDao prestamoDao = new PrestamoDao(HomeFragment.this.requireActivity());

        List<Autor> autorList = autorDao.listarModelos();
        List<Coleccion> coleccionList = coleccionDao.listarModelos();
        List<ColeccionLibro> coleccionLibroList = coleccionLibroDao.listarModelos();
        List<Compra> compraList = compraDao.listarModelos();
        List<Inventario> inventarioList = inventarioDao.listarModelos();
        List<Libro> libroList = libroDao.listarModelos();
        List<LibroDeseado> libroDeseadoList = libroDeseadoDao.listarModelos();
        List<Prestamo> autorList1 = prestamoDao.listarModelos();

        AutoresEditableRecyclerAdapter autoresEditableRecyclerAdapter = new AutoresEditableRecyclerAdapter(autorList, HomeFragment.this.requireContext(), getActivity());
        ColeccionEditableRecyclerAdapter coleccionEditableRecyclerAdapter = new ColeccionEditableRecyclerAdapter(coleccionList, HomeFragment.this.requireActivity(), getActivity());
        ColeccionLibroEditableRecyclerAdapter coleccionLibroEditableRecyclerAdapter = new ColeccionLibroEditableRecyclerAdapter(coleccionLibroList, HomeFragment.this.requireActivity(), getActivity());
        ComprasEditableRecyclerAdapter comprasEditableRecyclerAdapter = new ComprasEditableRecyclerAdapter(compraList, HomeFragment.this.requireActivity(), getActivity());
        InventariosEditableRecyclerAdapter inventariosEditableRecyclerAdapter = new InventariosEditableRecyclerAdapter(inventarioList, HomeFragment.this.requireActivity(), getActivity());
        LibroDeseadoEditableRecyclerAdapter libroDeseadoEditableRecyclerAdapter = new LibroDeseadoEditableRecyclerAdapter(libroDeseadoList, HomeFragment.this.requireActivity(), getActivity());
        LibrosEditableRecyclerAdapter librosEditableRecyclerAdapter = new LibrosEditableRecyclerAdapter(libroList, HomeFragment.this.requireActivity(), getActivity());
        PrestamosEditableRecyclerAdapter prestamosEditableRecyclerAdapter = new PrestamosEditableRecyclerAdapter(autorList1, HomeFragment.this.requireActivity(), getActivity());

        List<String> adapters = new ArrayList<>();
        adapters.add("Autores");
        adapters.add("Colecciones");
        adapters.add("Colecciones de libro");
        adapters.add("Compras");
        adapters.add("Inventarios");
        adapters.add("Libros deseados");
        adapters.add("Libros");
        adapters.add("Prestamos");
        //SpinnerAdapter spinnerAdapter = new ArrayAdapter<String>(HomeFragment.this.requireActivity(), R.layout.item_view, R.id.itemTextView, adapters);
        //spinner.setAdapter(spinnerAdapter);
        HomeSpinnerAdapter homeSpinnerAdapter = new HomeSpinnerAdapter(
                adapters,
                HomeFragment.this.requireActivity(),
                recyclerView,
                autoresEditableRecyclerAdapter,
                coleccionEditableRecyclerAdapter,
                coleccionLibroEditableRecyclerAdapter,
                comprasEditableRecyclerAdapter,
                inventariosEditableRecyclerAdapter,
                libroDeseadoEditableRecyclerAdapter,
                librosEditableRecyclerAdapter,
                prestamosEditableRecyclerAdapter
        );
        spinner.setAdapter(homeSpinnerAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}