package com.br.cotaacaoapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;

import com.br.cotaacaoapp.adapter.ItemAcaoAdapter;
import com.br.cotaacaoapp.controller.CarteiraController;
import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;
import com.br.cotaacaoapp.task.receiver.EventoReceberAtualizacao;

import java.util.ArrayList;

public class MainActivity extends Activity implements AtualizaAcaoDelegate {

    public CarteiraController carteiraController;
    public EventoReceberAtualizacao evento;

    private ItemAcaoAdapter adapter;
    private SwipeRefreshLayout swipeContainer;
    private ListView listCarteira;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        this.evento = EventoReceberAtualizacao.registraObservador(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        int layout = android.R.layout.simple_list_item_1;


        carteiraController = new CarteiraController(getAcaoApplication());

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                recarregaListaPapeis();
            }


        });

        adapter = new ItemAcaoAdapter(this, getAcaoApplication().getCarteira().getListPapeis());

        listCarteira = (ListView) findViewById(R.id.listCarteiras);
        listCarteira.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        recarregaListaPapeis();
    }

    private void recarregaListaPapeis() {

        carteiraController.atualizarValorAcao();

        swipeContainer.setRefreshing(false);
    }

    @Override
    public void atualizaPapel(PapelAtualizado papelAtualizado) {


        Log.i("MainActivity","atualizaPapel "+papelAtualizado.getCodigoPapel() + " " + papelAtualizado.getValorAtual());
        ArrayList<Papel> papeis =  getAcaoApplication().getCarteira().getPapeis();


        Papel papel = papeis.get(papeis.indexOf(papelAtualizado));
        papel.setPapelAtualizado(papelAtualizado);
        adapter.notifyDataSetChanged();


    }




    //Criaçao de menus
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        Log.i("MainActivity","criando Menu ");

        MenuInflater inflater =  getMenuInflater();
        inflater.inflate(R.menu.menu_carteira,menu);

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Integer itemClicado = item.getItemId();

        if(itemClicado == R.id.novoPapel)
        {
            Intent ir = new Intent(this, CadastroPapelActivity.class);
            startActivity(ir);

        }else if(itemClicado == R.id.apagarTudo)
        {
            carteiraController.apagarTudo();
            adapter.notifyDataSetChanged();
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

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }


}
