package com.example.lucas.projeto00.Principal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lucas.projeto00.Listas.EmpresarialListActivity;
import com.example.lucas.projeto00.Listas.TuristicoListActivity;
import com.example.lucas.projeto00.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void abrirPontosTuristicos(View v){
        Intent intent = new Intent(this, TuristicoListActivity.class);
        startActivity(intent);
    }

    public void abrirPontosEmpresariais(View v){
        Intent intent = new Intent(this, EmpresarialListActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Projeto","onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Projeto","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Projeto","onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Projeto","onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Projeto","onDestroy");
    }


}
