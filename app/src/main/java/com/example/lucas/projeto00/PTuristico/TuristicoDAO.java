package com.example.lucas.projeto00.PTuristico;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.lucas.projeto00.SQLITE.BancoDadosT;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 14/07/16.
 */
public class TuristicoDAO {

    SQLiteDatabase db;

    public TuristicoDAO(Context context){
        db = BancoDadosT.getDB(context);
    }

    public void salvar(Turistico turistico){
        ContentValues v = new ContentValues();
        v.put("nome",turistico.getNome());
        v.put("endereco",turistico.getEndereco());
        db.insert("tbl_turistico",null,v);
    }

    public void alterar(Turistico turistico){
        ContentValues v = new ContentValues();
        v.put("nome",turistico.getNome());
        v.put("endereco",turistico.getEndereco());

        String id = String.valueOf(turistico.getId());
        String[] wArgs = new String[]{id};

        db.update("tbl_turistico",v,"_id =?",wArgs);
    }

    public Turistico buscar(String id){
        String[] col = new String[]{"_id","nome","endereco"};
        String[] wArgs = new String[]{id};

        Cursor c = db.query("tbl_turisticos",col,"_id=?",wArgs,null,null,null);

        c.moveToFirst();

        Turistico turistico = new Turistico();
        turistico.setId(c.getLong(c.getColumnIndex("_id")));
        turistico.setNome(c.getString(c.getColumnIndex("nome")));
        turistico.setEndereco(c.getString(c.getColumnIndex("endereco")));

        return turistico;
    }

    public List<Turistico> listar(){

        String[] col = new String[]{"_id","nome","endereco"};
        Cursor c = db.query("tbl_turisticos",col,null,null,null,null,null);

        List<Turistico> turisticos = new ArrayList<Turistico>();
        if(c.moveToFirst()){
            do{
                Turistico turistico = new Turistico();
                turistico.setId(c.getLong(c.getColumnIndex("_id")));
                turistico.setNome(c.getString(c.getColumnIndex("nome")));
                turistico.setEndereco(c.getString(c.getColumnIndex("endereco")));

                turisticos.add(turistico);
            }while (c.moveToNext());
        }
        return turisticos;
    }

    public void excluir(String id){
        String[] wArgs = new String[]{id};
        db.delete("tbl_turisticos","_id=?",wArgs);
    }

}
