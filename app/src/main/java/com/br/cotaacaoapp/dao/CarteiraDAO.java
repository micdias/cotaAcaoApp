package com.br.cotaacaoapp.dao;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michel on 04/09/16.
 * classe que deve manter os papéis adquiridos
 */
public class CarteiraDAO extends DataBaseHelper{

    private static CarteiraDAO instance;
    private static final String TABLE_NAME = "CARTEIRA";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_CODIGO_PAPEL = "CODIGO_PAPEL";
    private static final String COLUMN_DESCRICAO_PAPEL = "DESCRICAO_PAPEL";
    private static final String COLUMN_DATA_COMPRA =  "DATA_COMPRA";
    private static final String COLUMN_VALOR_COMPRA = "VALOR_COMPRA";
    private static final String COLUMN_QUANTIDADE =  "QUANTIDADE";



    public static final String SCRIPT_CREATE = "CREATE TABLE "+TABLE_NAME +"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
            COLUMN_CODIGO_PAPEL+ " TEXT NOT NULL," +
            COLUMN_DESCRICAO_PAPEL+ " TEXT," +
            COLUMN_QUANTIDADE+ " INTEGER," +
            COLUMN_DATA_COMPRA+ " INTEGER NOT NULL," +
            COLUMN_VALOR_COMPRA+ " REAL NOT NULL" +
            ");";

    public static final String SCRIPT_DROP = "DROP TABLE IF EXISTS "+ TABLE_NAME + ";";
    public static final String SCRIPT_DELETE = "DELETE FROM "+ TABLE_NAME + ";";


    private CarteiraDAO(Context context)
    {
        init(context);
    }

    public static CarteiraDAO getInstance(Application context)
    {
        if(instance == null)
        {
            instance = new CarteiraDAO(context);
        }

        return instance;
    }


    public boolean inserirPapel(Papel papel) {

        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_CODIGO_PAPEL,papel.getCodigoPapel());
        contentValues.put(COLUMN_DESCRICAO_PAPEL,papel.getDescricaoPapel());
        contentValues.put(COLUMN_QUANTIDADE,papel.getQuantidade());
        contentValues.put(COLUMN_DATA_COMPRA,papel.getDataCompra().getTime());
        contentValues.put(COLUMN_VALOR_COMPRA,papel.getValorCompra());


        obterConexao().insert(TABLE_NAME,null,contentValues);

        fecharConexao();
        return true;
    }



    public Carteira carregarCarteira()
    {

        Carteira carteira = new Carteira();

        ArrayList<Papel> listPapeis = new ArrayList<>();

        String[] colunas = {
                COLUMN_ID,
                COLUMN_CODIGO_PAPEL,
                COLUMN_DESCRICAO_PAPEL,
                COLUMN_QUANTIDADE,
                COLUMN_DATA_COMPRA,
                COLUMN_VALOR_COMPRA
                };

        Cursor cursor =  obterConexao().query(TABLE_NAME, colunas, null, null, null, null, null);
        Papel papel;
        while(cursor.moveToNext()) {
            papel = new Papel();
            papel.setIdPapel(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            papel.setCodigoPapel(cursor.getString(cursor.getColumnIndex(COLUMN_CODIGO_PAPEL)));
            papel.setDescricaoPapel(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRICAO_PAPEL)));
            papel.setQuantidade(cursor.getInt(cursor.getColumnIndex(COLUMN_QUANTIDADE)));
            papel.setDataCompra(new Date(cursor.getInt(cursor.getColumnIndex(COLUMN_DATA_COMPRA))));
            papel.setValorCompra(cursor.getInt(cursor.getColumnIndex(COLUMN_VALOR_COMPRA)));

            listPapeis.add(papel);
        }
        fecharConexao();

        carteira.setListPapeis(listPapeis);

        return carteira;
    }


    public void apagarTudo() {

        obterConexao().delete(TABLE_NAME,null,null);
        fecharConexao();

    }
}
