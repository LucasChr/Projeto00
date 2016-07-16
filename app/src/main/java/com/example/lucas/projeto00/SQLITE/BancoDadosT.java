package com.example.lucas.projeto00.SQLITE;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by lucas on 15/07/16.
 */
public class BancoDadosT {

    private static final String NOME_BANCO = "Projeto";
    private static final int VERSAO_BANCO = 2;

    private static final String[] SCRIPT_DATABASE_DELETE = new String[] {"drop table if exists tbl_turisticos;"};
    private static final String[] SCRIPT_DATABASE_CREATE = new String[] {"create table tbl_turisticos(_id integer primary key, nome text, endereco text);"};

    private static SQLiteDatabase db;

    public static SQLiteDatabase getDB(Context ctx) {
        if (db == null) {
            SQLiteHelper dbHelper = new SQLiteHelper(ctx, NOME_BANCO, VERSAO_BANCO, SCRIPT_DATABASE_CREATE, SCRIPT_DATABASE_DELETE);
            db = dbHelper.getWritableDatabase();
        }
        return db;
    }
}
