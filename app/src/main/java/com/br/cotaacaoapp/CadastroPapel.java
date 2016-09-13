package com.br.cotaacaoapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.br.cotaacaoapp.controller.CarteiraController;
import com.br.cotaacaoapp.dto.Papel;

import java.util.Date;

public class CadastroPapel extends AppCompatActivity {

    CarteiraController carteira ;

    public CadastroPapel(AcaoApplication application)
    {
         carteira = new CarteiraController(application);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_papel);

        Button botaoSalvar =  (Button) findViewById(R.id.btnCadastrarPapel);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                carteira.inserirPapel(obterPapelDaTela());

                Intent ir = new Intent(CadastroPapel.this, MainActivity.class);
                startActivity(ir);
                finish();

            }
        });
    }

    public Papel obterPapelDaTela()
    {

        Papel papel = new Papel();

        papel.setCodigoPapel(((TextView)findViewById(R.id.textPapel)).getText().toString());
        papel.setDescricaoPapel(((TextView)findViewById(R.id.textPapelDescricao)).getText().toString());
        papel.setQuantidade(Integer.parseInt(((TextView)findViewById(R.id.textQuantidade)).getText().toString()));
        papel.setValorCompra(Integer.parseInt(((TextView)findViewById(R.id.textValorCompra)).getText().toString()));
       // papel.setDataCompra(new Date(((TextView)findViewById(R.id.textDataCompra)).getText().toString()));

        return papel;
    }
}

