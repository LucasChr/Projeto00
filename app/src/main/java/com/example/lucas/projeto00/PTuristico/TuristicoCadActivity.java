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
    final int MENU_BUSCAR = 3;

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
        menu.add(0, MENU_BUSCAR, 0, "Buscar");
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
        Turistico t = new Turistico();
        t.setNome(edtNome.getText().toString());
        dao.salvar(t);
        setResult(1);
        finish();
    }

    public void buscar() {
        Turistico turistico = dao.buscar(edtID.getText().toString());
        edtNome.setText(turistico.getNome());
        tvEndereco.setText(turistico.getEndereco());
        setResult(3);
    }

}

