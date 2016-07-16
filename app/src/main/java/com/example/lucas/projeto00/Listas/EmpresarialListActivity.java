package com.example.lucas.projeto00.Listas;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lucas.projeto00.PEmpresarial.Empresa;
import com.example.lucas.projeto00.PEmpresarial.EmpresaDAO;
import com.example.lucas.projeto00.PEmpresarial.EmpresarialCadActivity;
import com.example.lucas.projeto00.PEmpresarial.EmpresarialDadosActivity;
import com.example.lucas.projeto00.R;

import java.util.List;

public class EmpresarialListActivity extends ListActivity {

    final int MENU_NOVO = 1;
    final int MENU_CANCELAR = 2;
    EmpresaDAO dao;
    List<Empresa> empresas;
    EmpresarialListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_list);

        dao = new EmpresaDAO(this);
        empresas = dao.listar();
        adapter = new EmpresarialListAdapter(this, R.layout.activity_empresarial_list_item, empresas);
        setListAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, "NOVO");
        menu.add(0, 2, 0, "SAIR");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 1:
                Intent it = new Intent(this, EmpresarialCadActivity.class);
                it.putExtra("ID", "");
                startActivityForResult(it, 1);
                break;
            case 2:
                finish();
                break;
        }

        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        atualizaLista();
        if (resultCode == 1) {
            Toast.makeText(this, "Salvo com sucesso", Toast.LENGTH_LONG).show();
        } else if (resultCode == 2) {
            Toast.makeText(this, "Modificado com sucesso", Toast.LENGTH_LONG).show();
        }
    }

    public void atualizaLista() {
        List<Empresa> le = dao.listar();
        adapter.clear();
        adapter.addAll(le);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Empresa e = empresas.get(position);
        Intent it = new Intent(this, EmpresarialDadosActivity.class);
        it.putExtra("ID", String.valueOf(e.getId()));
        startActivityForResult(it, 2);
    }
}
