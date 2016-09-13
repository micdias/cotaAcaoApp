package com.br.cotaacaoapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.br.cotaacaoapp.adapter.ItemAcaoAdapter;
import com.br.cotaacaoapp.controller.CarteiraController;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;
import com.br.cotaacaoapp.task.receiver.EventoReceberAtualizacao;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements AtualizaAcaoDelegate {

    public CarteiraController carteira;
    public EventoReceberAtualizacao evento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.evento = EventoReceberAtualizacao.registraObservador(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recarregaListaPapeis();
            }

        });


        recarregaListaPapeis();


    }


    @Override
    protected void onResume() {
        super.onResume();
        recarregaListaPapeis();
    }

    private void recarregaListaPapeis() {

        int layout = android.R.layout.simple_list_item_1;
        carteira = new CarteiraController(getAcaoApplication());

        carteira.atualizarValorAcao();

        // ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, layout, carteira.getPapeis().toArray());
        poeNaTela();

    }

    private void poeNaTela()
    {

        ItemAcaoAdapter adapter = new ItemAcaoAdapter(this, carteira.getPapeis());

        ListView listCategoria = (ListView) findViewById(R.id.listCarteiras);
        listCategoria.setAdapter(adapter);

        SwipeRefreshLayout swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setRefreshing(false);

    }

    @Override
    public void atualizaPapel(PapelAtualizado papelAtualizado) {

        Log.i("MainActivity","atualizaPapel "+papelAtualizado.getCodigoPapel() + " " + papelAtualizado.getValorAtual());
        ArrayList<Papel> papeis =  carteira.getPapeis();

        Papel papel = papeis.get(papeis.indexOf(papelAtualizado));

        papel.setPapelAtualizado(papelAtualizado);

        poeNaTela();

    }


    //Criaçao de menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_carteira,menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Integer itemClicado = item.getItemId();

        if(itemClicado == R.id.novoPapel)
        {
            Intent ir = new Intent(this, CadastroPapel.class);
            startActivity(ir);

        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public AcaoApplication getAcaoApplication() {
        return (AcaoApplication)getApplication();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        this.evento.desregistra(getAcaoApplication());
    }


}
