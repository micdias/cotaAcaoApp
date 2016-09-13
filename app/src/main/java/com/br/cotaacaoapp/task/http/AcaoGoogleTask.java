package com.br.cotaacaoapp.task.http;

import android.app.Application;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.br.cotaacaoapp.AcaoApplication;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;
import com.br.cotaacaoapp.enumerators.Oscilacao;
import com.br.cotaacaoapp.task.receiver.EventoReceberAtualizacao;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Michel on 23/08/16.
 */
public class AcaoGoogleTask extends AsyncTask<Papel,Void,PapelAtualizado>{


    private static final String BASE_URL = "https://www.google.com/finance/info?q=";
    private static final String BOLSA = "BOVESPA";

    private final String USER_AGENT = "Mozilla/5.0";

    private static String PAPEL = "t";
    private static String ULTIMO_PRECO = "l";
    private static String OSCILACAO_PRECO ="c";
    private static String OSCILACAO_PRECO_FORMATADO ="c_fix";
    private static String FECHAMENTO = "lt_dts";
    private static String PRECO_ANTES_FECHAMENTO = "pcls_fix";
    private static String PERCENTUAL_DIA = "cp";

    private AcaoApplication application;

    public AcaoGoogleTask(AcaoApplication application)
    {
        this.application = application;
        this.application.registraTask(this);
    }

    @Override
    protected PapelAtualizado doInBackground(Papel... papel) {

        Log.i("AcaoGooogleTask.doInBackground","Papel "+ papel);

        if(papel == null || papel.length==0)
            return null;


        PapelAtualizado papelAtualizado = new PapelAtualizado();
        String codigoPapel = papel[0].getCodigoPapel();
        Log.i("atualizando", "atualizando acoes "+papel[0].getCodigoPapel());

        try {
            // obtendo o valor atualizado
            URL url = new URL(BASE_URL + codigoPapel);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // setando request como GET
            con.setRequestMethod("GET");

            //adicionando header
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            if(responseCode != 200)
                throw new Exception("Erro ao conectar URL" + url.toString());

            //Lendo as informacoes da requisicao
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //lendo os dados - retirando comentarios
            String responseStr = response.substring(2);
            JSONArray reader = new JSONArray(responseStr.toString());// response;
            JSONObject retorno = reader.getJSONObject(0);

            papelAtualizado.setCodigoPapel(codigoPapel);
            papelAtualizado.setValorAtual(retorno.getDouble(ULTIMO_PRECO));
            papelAtualizado.setOscilacaoPrecoDia(retorno.getDouble(OSCILACAO_PRECO));

            String oscilacaoFormatada = retorno.getString(OSCILACAO_PRECO_FORMATADO).substring(0,1);
            Oscilacao oscilacao = Oscilacao.NEUTRO;
            switch (oscilacaoFormatada) {
                case "+":
                    oscilacao = Oscilacao.POSITIVO;
                    break;
                case "-":
                    oscilacao = Oscilacao.NEGATIVO;
                    break;
            }
            papelAtualizado.setOscilacao(oscilacao);

            Log.i("AcaoGooogleTask.doInBackground","Papel atualizado "+papelAtualizado);


        }catch (Exception e)
        {
            Log.e("Erro======", e.getMessage());
        }
        return papelAtualizado;
    }


    @Override
    protected void onPostExecute(PapelAtualizado retorno) {
        super.onPostExecute(retorno);

        Log.i("AcaoGooogleTask.onPostExecute","Retorno "+ retorno);

        if(retorno != null){
            EventoReceberAtualizacao.notifica(this.application,retorno);
            this.application.desregistra(this);

        }
    }


    /**
     * id : Internal Google Security ID

     t : Ticker

     e : Exchange Name

     l : Last Price

     l_cur : ???

     ltt : Last Trade Time

     ltt : Last Trade Date/Time

     c : Change (in ccy) - formatted with +/- cp : Change (%)

     ccol :

     el : Last Price in Extended Hours Trading

     el_cur :

     elt: Last Trade Date/Time in Extended Hours Trading

     ec : Extended Hours Change (in ccy) - formatted with +/-

     ec : Extended Hours Change (%)

     eccol :
     */


}
