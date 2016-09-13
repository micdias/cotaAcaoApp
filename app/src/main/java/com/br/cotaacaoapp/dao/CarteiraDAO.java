package com.br.cotaacaoapp.dao;

import android.app.Application;
import android.content.Context;

import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;

/**
 * Created by Michel on 04/09/16.
 * classe que deve manter os papéis adquiridos
 */
public class CarteiraDAO extends DataBaseHelper{

    private static CarteiraDAO instance;
    private static final String TABLE_NAME = "CARTEIRA";
    public static final String SCRIPT_CREATE = "CREATE TABLE "+TABLE_NAME +"(id PRIMARY KEY identy, " +
            "descricao TEXT NOT NULL);";

    public static final String SCRIPT_DELETE = "DROP TABLE IF EXISTS "+ TABLE_NAME + ";";


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




    public Carteira carregarCarteira()
    {
        Carteira carteira = new Carteira();

        Papel papel = new Papel();
        papel.setCodigoPapel("ITSA4");
        papel.setValorCompra(7.08);

        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("FESA4");
        papel.setValorCompra(6.99);
        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("ESTC3");
        papel.setValorCompra(12.04);
        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("SUZB5");
        papel.setValorCompra(11.13);
        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("LIGT3");
        papel.setValorCompra(9.79);
        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("CMIG4");
        papel.setValorCompra(5.60);
        carteira.inserirItemCarteira(papel);

        papel = new Papel();
        papel.setCodigoPapel("EMBR3");
        papel.setValorCompra(14.94);
        carteira.inserirItemCarteira(papel);

        return carteira;
    }
}
