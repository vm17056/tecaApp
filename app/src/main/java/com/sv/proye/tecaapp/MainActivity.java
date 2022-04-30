package com.sv.proye.tecaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import com.sv.proye.tecaapp.views.ui.usuarios.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // instanceDaos();
        MainAsync mainAsync = new MainAsync();
        mainAsync.execute();
    }

    private void instanceDaos() {
//        try {
//            AutorDao autorDao = new AutorDao(MainActivity.this);
//            ColeccionDao coleccionDao = new ColeccionDao(MainActivity.this);
//            ColeccionLibroDao coleccionLibroDao = new ColeccionLibroDao(MainActivity.this);
//            CompraDao compraDao = new CompraDao(MainActivity.this);
//            FavoritoDao favoritoDao = new FavoritoDao(MainActivity.this);
//            HistorialLeidoDao historialLeidoDao = new HistorialLeidoDao(MainActivity.this);
//            InventarioDao inventarioDao = new InventarioDao(MainActivity.this);
//            LibroDao libroDao = new LibroDao(MainActivity.this);
//            LibroDeseadoDao libroDeseadoDao = new LibroDeseadoDao(MainActivity.this);
//            PrestamoDao prestamoDao = new PrestamoDao(MainActivity.this);
//            UsuarioDao usuarioDao = new UsuarioDao(MainActivity.this);
//        } catch (Exception ignore) {
//            ignore.printStackTrace();
//        }
    }

    private class MainAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            SystemClock.sleep(1000);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}