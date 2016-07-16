package com.example.lucas.projeto00.PEmpresarial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.example.lucas.projeto00.R;

public class EmpresarialCadActivity extends Activity {

    EmpresaDAO dao;

    final int MENU_SALVAR=1;
    final int MENU_ALTERAR=2;
    final int MENU_BUSCAR=3;
    final int MENU_EXCLUIR=4;

    TextView edtEndereco;
    EditText edtID, edtNome, edtTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empresarial_cad);

        edtID = (EditText)findViewById(R.id.empresarial_cad_edtID);
        edtNome = (EditText)findViewById(R.id.empresarial_cad_edtNome);
        edtTelefone = (EditText)findViewById(R.id.empresarial_cad_edtTelefone);
        edtEndereco = (TextView)findViewById(R.id.empresarial_cad_tvEndereco);

        dao = new EmpresaDAO(this);
        Intent intent= getIntent();
        if(!intent.getStringExtra("ID").equals("")){
            String id = intent.getStringExtra("ID");
            edtID.setText(id);
            buscar();
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0,1,0,"Salvar");
        menu.add(0,2,0,"Alterar");
        menu.add(0,3,0,"Buscar");
        menu.add(0,4,0,"Excluir");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
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

    public void salvar(){
        Empresa e = new Empresa();
        e.setNome(edtNome.getText().toString());
        e.setTelefone(edtTelefone.getText().toString());
        dao.salvar(e);
        setResult(1);
        finish();
    }

    public void alterar(){
        Empresa empresa = new Empresa();
        empresa.setId(new Long(edtID.getText().toString()));
        empresa.setNome(edtNome.getText().toString());
        empresa.setTelefone(edtTelefone.getText().toString());
        dao.alterar(empresa);
        setResult(2);
        finish();
    }

    public void buscar(){
        Empresa empresa = dao.buscar(edtID.getText().toString());
        edtNome.setText(empresa.getNome());
        edtTelefone.setText(empresa.getTelefone());
        setResult(3);
    }

    public void excluir(){
        dao.excluir(edtID.getText().toString());
        setResult(4);
        finish();
    }


}
