package com.br.cotaacaoapp.task.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.br.cotaacaoapp.AcaoApplication;
import com.br.cotaacaoapp.AtualizaAcaoDelegate;
import com.br.cotaacaoapp.dto.PapelAtualizado;
import com.br.cotaacaoapp.task.http.Constants;

import java.io.Serializable;

/**
 * Created by Michel on 09/09/16.
 */
public class EventoReceberAtualizacao extends BroadcastReceiver {

    AtualizaAcaoDelegate delegate;

   public static EventoReceberAtualizacao registraObservador(AtualizaAcaoDelegate delegate)
    {

        Log.i("i","Registrando");
        EventoReceberAtualizacao receiver =new EventoReceberAtualizacao();
        receiver.delegate = delegate;

        LocalBroadcastManager
                .getInstance(delegate.getAcaoApplication())
                .registerReceiver(receiver, new IntentFilter(Constants.INTENT_CONSULTA));
        return receiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equalsIgnoreCase(Constants.INTENT_CONSULTA)){
            Log.i("EventoReceberAtualizacao" , "onReceive ============================");

            PapelAtualizado papel = (PapelAtualizado)intent.getSerializableExtra(Constants.PAPEL_ATUALIZADO);

            this.delegate.atualizaPapel(papel);

        }



    }

    public static void notifica(Context context, Serializable resultado)
    {
        Intent intent = new Intent(Constants.INTENT_CONSULTA);
        intent.putExtra(Constants.PAPEL_ATUALIZADO, resultado);

        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);

    }

    public void desregistra(AcaoApplication acaoApplication) {
        LocalBroadcastManager.getInstance(acaoApplication).unregisterReceiver(this);

    }
}
