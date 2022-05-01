package com.sv.proye.tecaapp.views.ui.inventario;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.CompraDao;
import com.sv.proye.tecaapp.dto.InventarioDao;
import com.sv.proye.tecaapp.models.Compra;
import com.sv.proye.tecaapp.models.Inventario;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.adapters.spinners.InventarioSpinnerAdapter;

import java.util.Date;
import java.util.List;

public class ComprasFragment extends Fragment {

    private ComprasViewModel mViewModel;
    private Compra compraSelected;
    private AppCompatSpinner spinnerInventario;
    private NumberPicker inputCantidad;
    private DatePicker inputFechaCompra;
    private EditText inputPrecio;
    private EditText inputCodigo;
    private MaterialButton saveBtn;
    private Long maxDate = DateUtils.getActualDate().getTime();
    private CompraDao compraDao;
    private InventarioDao inventarioDao;

    public ComprasFragment() {
    }

    public ComprasFragment(Compra compraSelected) {
        this.compraSelected = compraSelected;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.compras_fragment, container, false);
        mViewModel = new ViewModelProvider(this).get(ComprasViewModel.class);
        spinnerInventario = view.findViewById(R.id.compra_inventario);
        inputCantidad = view.findViewById(R.id.compra_cantidad);
        inputFechaCompra = view.findViewById(R.id.compra_fechacompra);
        inputPrecio = view.findViewById(R.id.compra_precio);
        inputCodigo = view.findViewById(R.id.compra_codigocompra);
        saveBtn = view.findViewById(R.id.compra_savebtn);
        inputFechaCompra.setMaxDate(maxDate);
        inputCantidad.setMinValue(0);
        inputCantidad.setMaxValue(9999999);
        inputCantidad.setWrapSelectorWheel(true);
        compraDao = new CompraDao(ComprasFragment.this.requireActivity());
        inventarioDao = new InventarioDao(ComprasFragment.this.requireActivity());
        cargarInventarios();
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCompra();
            }
        });
        cargarDatos(compraSelected);
        return view;
    }

    private void cargarDatos(Compra o) {
        if (o != null) {
            inputCantidad.setValue(o.getCantidad());
            inputFechaCompra.getCalendarView().setDate(o.getFechaCompra().getTime());
            inputPrecio.setText(String.valueOf(o.getPrecioUnitario()));
            inputCodigo.setText(o.getCodigoCompra());
        }
    }

    private void guardarCompra() {
        if (validarDatos()) {
            Long registrado = compraDao.almacenarModelo(recolectarDatos());
            if (registrado == -1) {
                MessageUtils.displayFail(getResources().getString(R.string.database_error), ComprasFragment.this.requireActivity());
            } else {
                Compra compra = compraDao.buscarModeloPodId(registrado.intValue());
                if (compra != null) {
                    System.out.println("Compra registrada **** " + compra);
                    Inventario inventario = compra.getInventario();
                    Integer nuevaCantidad = inventario.getCantidad() + compra.getCantidad();
                    inventario.setCantidad(nuevaCantidad);
                    Integer actializado = inventarioDao.actualizarModelo(inventario);
                    if (actializado > 0) {
                        MessageUtils.displaySuccess("Compra Registrada", ComprasFragment.this.requireActivity());
                    } else {
                        MessageUtils.displayWarming(getResources().getString(R.string.database_update_error), ComprasFragment.this.requireActivity());
                    }
                }
            }
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), ComprasFragment.this.requireActivity());
        }
    }

    private void cargarInventarios() {
        List<Inventario> inventarios = inventarioDao.listarModelos();
        InventarioSpinnerAdapter spinnerAdapter = new InventarioSpinnerAdapter(inventarios, ComprasFragment.this.requireActivity());
        spinnerInventario.setAdapter(spinnerAdapter);
        if (compraSelected != null) {
            int pos = inventarios.indexOf(compraSelected.getInventario());
            spinnerInventario.setSelection(pos);
        }
        ;
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (InventarioSpinnerAdapter.inventarioSeleccionado == null) {
            spinnerInventario.setBackgroundColor(Color.RED);
            valid = false;
        } else {
            spinnerInventario.setBackgroundColor(Color.TRANSPARENT);
        }
        if (inputCantidad.getValue() < 1) {
            inputCantidad.setBackgroundColor(Color.RED);
            valid = false;
        }
        if (inputPrecio.getText().toString().isEmpty()) {
            inputCantidad.setBackgroundColor(Color.RED);
            valid = false;
        }
        if (inputCodigo.getText().toString().isEmpty()) {
            inputCantidad.setBackgroundColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Compra recolectarDatos() {
        Compra compra = new Compra();
        if (compraSelected != null) compra = compraSelected;
        compra.setInventario(InventarioSpinnerAdapter.inventarioSeleccionado);
        compra.setCantidad(inputCantidad.getValue());
        compra.setFechaCompra(new Date(inputFechaCompra.getCalendarView().getDate()));
        Double precioUnitario = 0.00;
        try {
            precioUnitario = Double.parseDouble(inputPrecio.getText().toString());
        } catch (Exception ignore) {
        }
        compra.setPrecioUnitario(precioUnitario);
        compra.setCodigoCompra(inputCodigo.getText().toString());
        return compra;
    }


}