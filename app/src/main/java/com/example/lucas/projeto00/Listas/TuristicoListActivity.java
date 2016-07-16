package com.example.lucas.projeto00.Listas;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;
import com.example.lucas.projeto00.PTuristico.Turistico;
import com.example.lucas.projeto00.PTuristico.TuristicoCadActivity;
import com.example.lucas.projeto00.PTuristico.TuristicoDAO;
import com.example.lucas.projeto00.PTuristico.TuristicoDadosActivity;
import com.example.lucas.projeto00.R;

import java.util.List;

public class TuristicoListActivity extends ListActivity {

    final int MENU_NOVO = 1;
    final int MENU_SAIR = 2;
    TuristicoDAO dao;
    List<Turistico> turisticos;
    TuristicoListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_turistico_list);
        dao = new TuristicoDAO(this);
        turisticos = dao.listar();
        adapter = new TuristicoListAdapter(this, R.layout.activity_turistico_list_item, turisticos);
        setListAdapter(adapter);

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle(R.string.dialog_test);
        dialog.setMessage("Carregamento lento...");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCancelable(true);
        dialog.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (valor <= max) {
                    simularDownload();
                    dialog.setProgress(valor);
                }
                dialog.dismiss();
            }
        }).start();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_NOVO, 0, "NOVO");
        menu.add(0, MENU_SAIR, 0, "SAIR");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case 1:
                Intent it = new Intent(this, TuristicoCadActivity.class);
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
        List<Turistico> tu = dao.listar();
        adapter.clear();
        adapter.addAll(tu);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Turistico t = turisticos.get(position);
        Intent it = new Intent(this, TuristicoDadosActivity.class);
        it.putExtra("ID", String.valueOf(t.getId()));
        startActivityForResult(it, 2);
    }

    int valor = 0;
    int max =50;
    public void simularDownload() {
        valor++;
        try {
            Thread.sleep(100);
        } catch (Exception e) {

        }
    }
}

