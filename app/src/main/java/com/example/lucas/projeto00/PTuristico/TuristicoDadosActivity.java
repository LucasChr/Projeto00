package com.example.lucas.projeto00.PTuristico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lucas.projeto00.PEmpresarial.Empresa;
import com.example.lucas.projeto00.PEmpresarial.EmpresaDAO;
import com.example.lucas.projeto00.R;

public class TuristicoDadosActivity extends Activity {

    EmpresaDAO dao;
    EditText edtID;
    TextView tvNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico_dados);

        edtID = (EditText) findViewById(R.id.turistico_dados_edtID);
        tvNome = (TextView) findViewById(R.id.turistico_dados_tvNome);

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
        Intent it = new Intent(this, TuristicoCadActivity.class);
        startActivity(it);
    }

    public void buscar() {
        Empresa empresa = dao.buscar(edtID.getText().toString());
        tvNome.setText(empresa.getNome());
        setResult(3);
    }
}


