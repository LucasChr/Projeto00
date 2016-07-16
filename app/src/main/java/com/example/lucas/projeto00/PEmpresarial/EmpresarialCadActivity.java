package com.example.lucas.projeto00.PEmpresarial;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.projeto00.R;

public class EmpresarialCadActivity extends Activity {

    EmpresaDAO dao;
    TextView edtEndereco;
    EditText edtID, edtNome, edtTelefone, edtSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_cad);

        edtID = (EditText) findViewById(R.id.empresarial_cad_edtID);
        edtNome = (EditText) findViewById(R.id.empresarial_cad_edtNome);
        edtTelefone = (EditText) findViewById(R.id.empresarial_cad_edtTelefone);
        edtEndereco = (TextView) findViewById(R.id.empresarial_cad_tvEndereco);
        edtSite = (EditText)findViewById(R.id.empresarial_cad_edtSite);

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
        menu.add(0, 1, 0, "Salvar");
        menu.add(0, 3, 0, "Buscar");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 1:
                salvar();
                break;

            case 3:
                buscar();
                break;
        }

        return true;
    }

    public void salvar() {
        Empresa e = new Empresa();
        e.setNome(edtNome.getText().toString());
        e.setTelefone(edtTelefone.getText().toString());
        e.setSite(edtSite.getText().toString());
        e.setEndereco(edtEndereco.getText().toString());
        dao.salvar(e);
        setResult(1);
        finish();
    }

    public void buscar() {
        Empresa empresa = dao.buscar(edtID.getText().toString());
        edtNome.setText(empresa.getNome());
        edtTelefone.setText(empresa.getTelefone());
        edtSite.setText(empresa.getSite());
        edtEndereco.setText(empresa.getEndereco());
        setResult(3);
    }

}
