package com.br.cotaacaoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.cotaacaoapp.controller.CarteiraController;
import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CadastroPapelActivity extends Activity {

    private CarteiraController carteiraController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carteiraController = new CarteiraController(getAcaoApplication());

        setContentView(R.layout.activity_cadastro_papel);

        Button botaoSalvar =  (Button) findViewById(R.id.btnCadastrarPapel);
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Papel papel = obterPapelDaTela();
                if(papel != null) {
                    carteiraController.inserirPapel(obterPapelDaTela());

                    Intent ir = new Intent(CadastroPapelActivity.this, MainActivity.class);
                    startActivity(ir);
                    finish();
                }
            }
        });


        AutoCompleteTextView autComp;

        String[] acoes = getAcaoApplication().getResources().getStringArray(R.array.array_acoes);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, acoes);
        autComp = (AutoCompleteTextView) findViewById(R.id.textPapel);
        autComp.setAdapter(adapter);
    }

    public Papel obterPapelDaTela()
    {

        Papel papel = new Papel();
        Date data;

        try {

            papel.setCodigoPapel(((AutoCompleteTextView)findViewById(R.id.textPapel)).getText().toString());
            papel.setDescricaoPapel(((TextView)findViewById(R.id.textPapelDescricao)).getText().toString());
            papel.setQuantidade(Integer.parseInt(((TextView)findViewById(R.id.textQuantidade)).getText().toString()));
            papel.setValorCompra(Double.parseDouble(((TextView)findViewById(R.id.textValorCompra)).getText().toString()));

            String dataTela = ((TextView)findViewById(R.id.textDataCompra)).getText().toString();
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

            try {
                data = new Date(format.parse(dataTela).getTime());
            }catch (Exception e)
            {
                data = new Date();
            }
            papel.setDataCompra(data);

        }catch (Exception e) {
            Toast.makeText(this.getApplicationContext(),"Erro nos dados de entrada",Toast.LENGTH_LONG).show();
            papel = null;
        }

        return papel;
    }



    public AcaoApplication getAcaoApplication() {
        return (AcaoApplication)getApplication();
    }




}

