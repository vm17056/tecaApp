package com.sv.proye.tecaapp.views.ui.inventario;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.InventarioDao;
import com.sv.proye.tecaapp.dto.PrestamoDao;
import com.sv.proye.tecaapp.dto.UsuarioDao;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.models.Prestamo;
import com.sv.proye.tecaapp.models.Usuario;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.adapters.spinners.InventarioSpinnerAdapter;
import com.sv.proye.tecaapp.views.adapters.spinners.UsuarioSpinnerAdapter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PrestamosFragment extends Fragment {

    private PrestamosViewModel mViewModel;
    private AppCompatSpinner spinnerInventario;
    private AppCompatSpinner spinnerUsuario;
    private NumberPicker inputCantidad;
    private DatePicker inputFechaPrestamo;
    private DatePicker inputFechaEntrega;
    private ToggleButton inputEntregado;
    private MaterialButton saveBtn;
    private Long maxDate = DateUtils.getActualDate().getTime();
    PrestamoDao prestamoDao;

    public static PrestamosFragment newInstance() {
        return new PrestamosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.prestamos_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(PrestamosViewModel.class);
        spinnerInventario = view.findViewById(R.id.prestamo_inventario);
        spinnerUsuario = view.findViewById(R.id.prestamo_usuario);
        inputCantidad = view.findViewById(R.id.prestamo_cantidad);
        inputFechaPrestamo = view.findViewById(R.id.prestamo_fechaprestamo);
        inputFechaEntrega = view.findViewById(R.id.prestamo_fechaentrega);
        inputEntregado = view.findViewById(R.id.prestamo_entregado);
        saveBtn = view.findViewById(R.id.prestamo_savebtn);
        inputFechaEntrega.setMaxDate(maxDate);
        inputFechaPrestamo.setMaxDate(maxDate + 31536000000L);
        inputCantidad.setMinValue(0);
        inputCantidad.setMaxValue(1);
        inputCantidad.setWrapSelectorWheel(true);
        prestamoDao = new PrestamoDao(PrestamosFragment.this.requireActivity());
        cargarInventarios();
        cargarUsuarios();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarPrestamo();
            }
        });
        return view;
    }

    private void guardarPrestamo() {
        if (validarDatos()) {
            Long registrado = prestamoDao.almacenarModelo(recolectarDatos());
            if (registrado == -1)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), PrestamosFragment.this.requireActivity());
            else
                MessageUtils.displaySuccess("Prestamo Registrado", PrestamosFragment.this.requireActivity());
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), PrestamosFragment.this.requireActivity());
        }
    }

    private void cargarInventarios() {
        InventarioDao inventarioDao = new InventarioDao(PrestamosFragment.this.requireActivity());
        List<Inventario> inventarios = inventarioDao.listarModelos();
        List<Inventario> inventarioAviable = new ArrayList<>();
        if (!inventarios.isEmpty())
            for (Inventario inv : inventarios) {
                if (inv.getCantidad() != null && inv.getCantidad() > 0) {
                    inventarioAviable.add(inv);
                }
            }
        ;
        InventarioSpinnerAdapter spinnerAdapter = new InventarioSpinnerAdapter(inventarioAviable, PrestamosFragment.this.requireActivity());
        spinnerInventario.setAdapter(spinnerAdapter);
        spinnerInventario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Inventario selected = inventarioAviable.get(position);
                inputCantidad.setMaxValue(selected.getCantidad());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void cargarUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDao(PrestamosFragment.this.requireActivity());
        List<Usuario> usuarios = usuarioDao.listarModelos();
        UsuarioSpinnerAdapter spinnerAdapter = new UsuarioSpinnerAdapter(usuarios, PrestamosFragment.this.requireActivity());
        spinnerUsuario.setAdapter(spinnerAdapter);
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (InventarioSpinnerAdapter.inventarioSeleccionado == null) {
            spinnerInventario.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerInventario.setBackgroundColor(Color.TRANSPARENT);
        }
        if (UsuarioSpinnerAdapter.usuarioSeleccionado == null) {
            spinnerUsuario.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerUsuario.setBackgroundColor(Color.TRANSPARENT);
        }
        if (inputCantidad.getValue() < 1) {
            inputCantidad.setBackgroundColor(Color.RED);
            valid = false;
        }
        if (inputEntregado.getText().toString().isEmpty()) {
            inputEntregado.setHintTextColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Prestamo recolectarDatos() {
        Prestamo compra = new Prestamo();
        compra.setInventario(InventarioSpinnerAdapter.inventarioSeleccionado);
        compra.setUsuario(UsuarioSpinnerAdapter.usuarioSeleccionado);
        compra.setCantidad(inputCantidad.getValue());
        compra.setFechaPrestamo(new Date(inputFechaPrestamo.getCalendarView().getDate()));
        compra.setFechaEntrega(new Date(inputFechaEntrega.getCalendarView().getDate()));
        compra.setDevuelto(getResources().getString(R.string.yes).equalsIgnoreCase(inputEntregado.getText().toString()));
        return compra;
    }
}