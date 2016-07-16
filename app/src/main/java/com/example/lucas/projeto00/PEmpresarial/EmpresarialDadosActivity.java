package com.example.lucas.projeto00.PEmpresarial;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.lucas.projeto00.R;

public class EmpresarialDadosActivity extends AppCompatActivity {

    EditText edtTelefone, edtSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_dados);


    }

    public void Ligar(View v) {
        Uri uri = Uri.parse("tel:" + edtTelefone.getText());
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        startActivity(it);
    }

    public void abrirSite(View v) {
        goToUrl("http:" + edtSite.getText());
    }

    public void goToUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }
}
