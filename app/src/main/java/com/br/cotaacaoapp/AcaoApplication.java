package com.br.cotaacaoapp;

import android.app.Application;
import android.os.AsyncTask;

import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.PapelAtualizado;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Michel on 09/09/16.
 */
public class AcaoApplication extends Application {

    private List<AsyncTask> tasks = new ArrayList<>();
    private Carteira carteira;

    @Override
    public void onTerminate() {
        for(AsyncTask task : tasks)
        {
            task.cancel(true);
        }
    }

    public void registraTask(AsyncTask task)
    {
        tasks.add(task);
    }

    public void desregistra(AsyncTask task)
    {
        tasks.remove(task);
    }


    public Carteira getCarteira() {
        return carteira;
    }

    public void setCarteira(Carteira carteira) {
        this.carteira = carteira;
    }
}
