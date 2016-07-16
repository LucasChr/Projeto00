package com.example.lucas.projeto00.PEmpresarial;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.example.lucas.projeto00.SQLITE.BancoDadosE;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 14/07/16.
 */
public class EmpresaDAO {

    SQLiteDatabase db;

    public EmpresaDAO(Context context){
        db = BancoDadosE.getDB(context);
    }


    public void salvar(Empresa empresa){
        ContentValues values = new ContentValues();
        values.put("nome",empresa.getNome());
        values.put("telefone",empresa.getTelefone());
        values.put("endereco",empresa.getEndereco());
        values.put("site",empresa.getSite());
        db.insert("tbl_empresas",null,values);
    }

    public void alterar(Empresa empresa){
        ContentValues values = new ContentValues();
        values.put("nome",empresa.getNome());
        values.put("telefone",empresa.getTelefone());
        values.put("endereco",empresa.getEndereco());
        values.put("site",empresa.getSite());

        String id = String.valueOf(empresa.getId());
        String[] whereArgs = new String[]{id};

        db.update("tbl_empresas",values,"_id = ?",whereArgs);
    }

    public Empresa buscar(String id){


        String[] colunas = new String[]{"_id","nome","telefone","endereco"};
        String[] whereArgs = new String[]{id};

        Cursor c = db.query("tbl_empresas",colunas,"_id = ?",whereArgs,null,null,null);

        c.moveToFirst();

        Empresa empresa = new Empresa();
        empresa.setId(c.getLong(c.getColumnIndex("_id")));
        empresa.setNome(c.getString(c.getColumnIndex("nome")));
        empresa.setTelefone(c.getString(c.getColumnIndex("telefone")));
        empresa.setEndereco(c.getString(c.getColumnIndex("endereco")));
        empresa.setSite(c.getString(c.getColumnIndex("site")));

        return empresa;
    }

    public List<Empresa> listar(){

        String[]colunas =  new String[]{"_id","nome","telefone","endereco"};
        Cursor c = db.query("tbl_empresas",colunas,null,null,null,null,null);

        List<Empresa> empresas = new ArrayList<Empresa>();
        if(c.moveToFirst()){
            do {
                Empresa empresa = new Empresa();
                empresa.setId(c.getLong(c.getColumnIndex("_id")));
                empresa.setNome(c.getString(c.getColumnIndex("nome")));
                empresa.setTelefone(c.getString(c.getColumnIndex("telefone")));

                empresas.add(empresa);

                Log.i("lista","Empresa");
            }while (c.moveToNext());
        }
        return  empresas;
    }


    public void excluir(String id){
        String[] whereArgs = new String[]{id};
        db.delete("tbl_empresas","_id = ?",whereArgs);
    }
}
