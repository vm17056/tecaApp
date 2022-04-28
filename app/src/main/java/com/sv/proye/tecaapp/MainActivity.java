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
        MainAsync mainAsync = new MainAsync();
        mainAsync.execute();
    }

    private class MainAsync extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... strings) {
            SystemClock.sleep(3000);
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

}