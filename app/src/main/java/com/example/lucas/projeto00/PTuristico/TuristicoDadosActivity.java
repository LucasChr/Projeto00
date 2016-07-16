package com.example.lucas.projeto00.PTuristico;

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

import com.example.lucas.projeto00.PEmpresarial.Empresa;
import com.example.lucas.projeto00.PEmpresarial.EmpresaDAO;
import com.example.lucas.projeto00.R;

public class TuristicoDadosActivity extends Activity {

    EmpresaDAO dao;
    EditText edtID;
    TextView tvNome, tvLatitude, tvLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico_dados);

        edtID = (EditText) findViewById(R.id.turistico_dados_edtID);
        tvNome = (TextView) findViewById(R.id.turistico_dados_tvNome);
        tvLatitude = (TextView) findViewById(R.id.turistico_dados_tvLatitude);
        tvLongitude = (TextView) findViewById(R.id.turistico_dados_tvLongitude);

        dao = new EmpresaDAO(this);
        Intent intent = getIntent();
        if (!intent.getStringExtra("ID").equals("")) {
            String id = intent.getStringExtra("ID");
            edtID.setText(id);
            buscar();
        }
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

    public void buscar() {
        Empresa empresa = dao.buscar(edtID.getText().toString());
        tvNome.setText(empresa.getNome());
        tvLatitude.setText(empresa.getLatitude());
        tvLongitude.setText(empresa.getLongitude());
        setResult(3);
    }

    public void abrirMapaT(View v) {
        //Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll" + tvLatitude.getText() +","+tvLongitude.getText()));
        Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("geo: " + tvLatitude.getText() + "," + tvLongitude.getText() + "?z=17"));
        startActivity(it);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("valor", tvLongitude.getText().toString());
        outState.putString("valor", tvLatitude.getText().toString());
        outState.putString("valor", tvNome.getText().toString());
        Log.i("bundle", "save");
    }

    @Override
    protected void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        tvLatitude.setText(bundle.getString("valor"));
        tvLongitude.setText(bundle.getString("valor"));
        tvNome.setText(bundle.getString("valor"));
        Log.i("bundle", "restore");
    }

}


