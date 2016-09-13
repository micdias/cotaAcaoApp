package com.br.cotaacaoapp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Michel on 20/07/16.
 */
public class DataBaseHelper {


    private SQLiteDatabase dataBase = null;
    DataBaseConfig databaseConfig = null;
    Context context = null;

    protected void init(Context context)
    {
        this.context = context;

    }

    protected SQLiteDatabase obterConexao()
    {
        DataBaseConfig databaseConfig = DataBaseConfig.getInstance(context);
        dataBase = databaseConfig.getWritableDatabase();
        return dataBase;
    }

    protected void fecharConexao() {
        if(dataBase != null && dataBase.isOpen())
            dataBase.close();
    }
}
