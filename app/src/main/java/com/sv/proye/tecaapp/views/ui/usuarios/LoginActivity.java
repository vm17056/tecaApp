package com.sv.proye.tecaapp.views.ui.usuarios;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.sv.proye.tecaapp.R;
import com.sv.proye.tecaapp.dto.UsuarioDao;
import com.sv.proye.tecaapp.models.Usuario;
import com.sv.proye.tecaapp.utils.MessageUtils;
import com.sv.proye.tecaapp.views.MenuInicioActivity;

public class LoginActivity extends AppCompatActivity {

    EditText inputUsuario;
    EditText inputPassword;
    MaterialButton btnLogin;
    MaterialButton btnRegister;
    UsuarioDao usuarioDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usuarioDao = new UsuarioDao(this);
        inputUsuario = findViewById(R.id.login_username);
        inputPassword = findViewById(R.id.login_userpass);

        btnLogin = findViewById(R.id.login_loginbtn);
        btnRegister = findViewById(R.id.login_registerbtn);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegistrarseActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validarDatos()) {
                    validarUsuario();
                }
            }
        });
    }

    private Boolean validarDatos() {
        boolean valid = true;
        if (inputUsuario.getText().toString().isEmpty()) {
            inputUsuario.setHintTextColor(Color.RED);
            valid = false;
        }
        if (inputPassword.getText().toString().isEmpty()) {
            inputPassword.setHintTextColor(Color.RED);
            valid = false;
        }
        if (!inputPassword.getText().toString().isEmpty() && inputPassword.getText().toString().length() < 6) {
            Toast.makeText(this, "Ingresa mas de 5 caracteres de contraseña", Toast.LENGTH_SHORT).show();
            inputPassword.setHintTextColor(Color.RED);
            valid = false;
        }
        return valid;
    }

    int validarTint(int id) {
        EditText editText = findViewById(id);
        if (editText.getCurrentHintTextColor() == Color.RED) {
            return Color.RED;
        } else {
            return Color.WHITE;
        }
    }

    private void validarUsuario() {
        Usuario usuario = new Usuario();
        usuario.setUserName(inputUsuario.getText().toString());
        usuario.setUserPass(inputPassword.getText().toString());
        usuario = usuarioDao.validarUsuario(usuario);
        if (usuario != null) {
            MessageUtils.displaySuccess("Bienvenido", LoginActivity.this);
            Intent intent = new Intent(LoginActivity.this, MenuInicioActivity.class);
            startActivity(intent);
        } else {
            MessageUtils.displayFail("Algo salio mal con los datos de usuario, intenta nuevamente", LoginActivity.this);
        }
    }
}