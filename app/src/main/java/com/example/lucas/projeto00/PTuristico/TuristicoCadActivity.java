package com.example.lucas.projeto00.PTuristico;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import com.example.lucas.projeto00.R;

public class TuristicoCadActivity extends Activity {

    TuristicoDAO dao;

    final int MENU_SALVAR = 1;
    final int MENU_ALTERAR = 2;
    final int MENU_BUSCAR = 3;
    final int MENU_EXCLUIR = 4;

    TextView tvEndereco;
    EditText edtID, edtNome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico_cad);
        edtID = (EditText) findViewById(R.id.turistico_cad_edtID);
        edtNome = (EditText) findViewById(R.id.turistico_cad_edtNome);
        tvEndereco = (TextView) findViewById(R.id.turistico_cad_tvEndereco);

        dao = new TuristicoDAO(this);
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
        menu.add(0, MENU_SALVAR, 0, "Salvar");
        menu.add(0, MENU_ALTERAR, 0, "Alterar");
        menu.add(0, MENU_BUSCAR, 0, "Buscar");
        menu.add(0, MENU_EXCLUIR, 0, "Excluir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 1:
                salvar();
                break;

            case 2:
                alterar();
                break;

            case 3:
                buscar();
                break;

            case 4:
                excluir();
                break;
        }

        return true;
    }

    public void salvar() {
        Turistico t = new Turistico();
        t.setNome(edtNome.getText().toString());

        dao.salvar(t);
        setResult(1);
        finish();
    }

    public void alterar() {
        Turistico turistico = new Turistico();
        turistico.setId(new Long(edtID.getText().toString()));
        turistico.setNome(edtNome.getText().toString());
        turistico.setEndereco(tvEndereco.getText().toString());
        dao.alterar(turistico);
        setResult(2);
        finish();
    }

    public void buscar() {
        Turistico turistico = dao.buscar(edtID.getText().toString());
        edtNome.setText(turistico.getNome());
        tvEndereco.setText(turistico.getEndereco());
        setResult(3);
    }

    public void excluir() {
        dao.excluir(edtID.getText().toString());
        setResult(4);
        finish();
    }
}

