package com.example.lucas.projeto00.PEmpresarial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.lucas.projeto00.R;


public class EmpresarialDadosActivity extends Activity {

    EmpresaDAO dao;
    EditText edtID;
    TextView tvNome, tvTelefone, tvSite, tvLatitude, tvLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_dados);

        edtID = (EditText) findViewById(R.id.empresarial_dados_edtID);
        tvNome = (TextView) findViewById(R.id.empresarial_dados_tvNome);
        tvTelefone = (TextView) findViewById(R.id.empresarial_dados_tvTelefone);
        tvSite = (TextView) findViewById(R.id.empresarial_dados_tvSite);
        tvLatitude = (TextView) findViewById(R.id.empresarial_dados_tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.empresarial_dados_tvLongitude);

        dao = new EmpresaDAO(this);
        Intent intent = getIntent();
        if (!intent.getStringExtra("ID").equals("")) {
            String id = intent.getStringExtra("ID");
            edtID.setText(id);
            buscar();
        }
    }

    public void Ligar(View v) {
        Uri uri = Uri.parse("tel:" + tvTelefone.getText());
        Intent it = new Intent(Intent.ACTION_CALL, uri);
        startActivity(it);
    }

    public void abrirSite(View v) {
        goToUrl("http:" + tvSite.getText());
    }

    public void goToUrl(String url) {
        Uri uri = Uri.parse(url);
        Intent it = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(it);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 2, 0, "Alterar");
        menu.add(0, 4, 0, "Excluir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 2:
                alterar();
                break;

            case 4:
                excluir();
                break;
        }

        return true;
    }


    public void excluir() {
        dao.excluir(edtID.getText().toString());
        setResult(4);
        Toast.makeText(this, "Excluído!", Toast.LENGTH_LONG).show();
        finish();
    }

    public void alterar() {
        Intent it = new Intent(this, EmpresarialCadActivity.class);
        startActivity(it);
    }

    public void buscar() {
        Empresa empresa = dao.buscar(edtID.getText().toString());
        tvNome.setText(empresa.getNome());
        tvTelefone.setText(empresa.getTelefone());
        tvSite.setText(empresa.getSite());
        tvLatitude.setText(empresa.getLatitude());
        tvLongitude.setText(empresa.getLongitude());
        setResult(3);
    }

    public void abrirMapa(View v) {
        //Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll" + tvLatitude.getText() +","+tvLongitude.getText()));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: " + tvLatitude.getText() + "," + tvLongitude.getText() + "?z=17"));
        startActivity(it);
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("valor", edtID.getText().toString());
        outState.putString("valor", tvLongitude.getText().toString());
        outState.putString("valor", tvNome.getText().toString());
        outState.putString("valor", tvLatitude.getText().toString());
        outState.putString("valor", tvSite.getText().toString());
        outState.putString("valor", tvTelefone.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        edtID.setText(bundle.getString("valor"));
        tvSite.setText(bundle.getString("valor"));
        tvTelefone.setText(bundle.getString("valor"));
        tvLatitude.setText(bundle.getString("valor"));
        tvLongitude.setText(bundle.getString("valor"));
        tvNome.setText(bundle.getString("valor"));
        Log.i("bundle", "restore");
    }
}