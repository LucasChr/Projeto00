package com.example.lucas.projeto00.Principal;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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


}
