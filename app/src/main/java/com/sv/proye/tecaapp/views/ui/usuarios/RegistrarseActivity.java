package com.sv.proye.tecaapp.views.ui.usuarios;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.UsuarioDao;
import com.sv.proye.tecaapp.models.Usuario;
import com.sv.proye.tecaapp.utils.DateUtils;
import com.sv.proye.tecaapp.utils.MessageUtils;

import java.util.Date;

public class RegistrarseActivity extends AppCompatActivity {

    private EditText inputNombre;
    private EditText inputApellido;
    private DatePicker inputFechaNacimiento;
    private EditText inputTelefono;
    private EditText inputEmail;
    private EditText inputUsername;
    private EditText inputUserpass;
    private MaterialButton btnSave;
    private Long minDate = DateUtils.getDateFromStringFormat("1910-01-01", DateUtils.FORMAT_YYYY_MM_DD).getTime();
    private Long maxDate = DateUtils.getActualDate().getTime();
    private UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        inputNombre = findViewById(R.id.register_name);
        inputApellido = findViewById(R.id.register_lastname);
        inputFechaNacimiento = findViewById(R.id.register_fechanacimiento);
        inputTelefono = findViewById(R.id.register_telefono);
        inputEmail = findViewById(R.id.register_email);
        inputUsername = findViewById(R.id.register_username);
        inputUserpass = findViewById(R.id.register_userpass);
        btnSave = findViewById(R.id.register_savebtn);
        inputFechaNacimiento.setMinDate(minDate);
        inputFechaNacimiento.setMaxDate(maxDate);
        usuarioDao = new UsuarioDao(RegistrarseActivity.this);

        // getLayoutInflater()
        // getWindow().getDecorView().getRootView();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarUsuario();
            }
        });
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (inputNombre.getText().toString().isEmpty()) {
            inputNombre.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputApellido.getText().toString().isEmpty()) {
            inputApellido.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputFechaNacimiento.getCalendarView().getDate() == DateUtils.getActualDate().getTime()) {
            inputFechaNacimiento.setBackgroundColor(Color.RED);
            valid = false;
        }
        if (inputTelefono.getText().toString().isEmpty()) {
            inputTelefono.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputEmail.getText().toString().isEmpty()) {
            inputEmail.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputUsername.getText().toString().isEmpty()) {
            inputUsername.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputUserpass.getText().toString().isEmpty()) {
//            Toast.makeText(RegistrarseActivity.this, "Ingrese Nombre", Toast.LENGTH_LONG).show();
            inputUserpass.setHintTextColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    private Usuario recolectarDatos() {
        Usuario usuario = new Usuario();
        usuario.setNombre(inputNombre.getText().toString());
        usuario.setApellido(inputApellido.getText().toString());
        usuario.setFechaNacimiento(new Date(inputFechaNacimiento.getCalendarView().getDate()));
        usuario.setTelefono(inputTelefono.getText().toString());
        usuario.setEmail(inputEmail.getText().toString());
        usuario.setUserName(inputUsername.getText().toString());
        usuario.setUserPass(inputUserpass.getText().toString());
        System.out.println(usuario);
        return usuario;
    }

    private void guardarUsuario() {
        if (validarDatos()) {
            Long registrado = usuarioDao.almacenarModelo(recolectarDatos());
            if (registrado == -1)
                MessageUtils.displayFail(getResources().getString(R.string.database_error), RegistrarseActivity.this);
            else
                MessageUtils.displaySuccess("Usuario Registrado", RegistrarseActivity.this);
        } else {
            MessageUtils.displayWarming(getResources().getString(R.string.validation_invalid), RegistrarseActivity.this);
        }
    }

}