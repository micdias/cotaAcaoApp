package com.br.cotaacaoapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.br.cotaacaoapp.R;

import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Michel on 11/09/16.
 */
public class ItemAcaoAdapter extends BaseAdapter {

    private Context context;
    private List<Papel> papeis;
    public ItemAcaoAdapter(Context context,List<Papel> papeis)
    {
        this.context = context;
        this.papeis = papeis;
    }


    @Override
    public int getCount() {
        return papeis.size();
    }

    @Override
    public Object getItem(int position) {
        return papeis.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //super.getView()
        Papel papel = (Papel) getItem(position);

        ViewHolder holder;
       // int layout = getItemViewType(position)==0?R.layout.publicacao_linha_par:R.layout.publicacao_linha_impar;
        int layout = R.layout.item_lista_main_activity;

        if(convertView == null)
        {
            convertView = LayoutInflater.from(context).inflate(layout, parent, false);
            Log.i("ItemAdapter.getView","Criou nova Linha");
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            Log.i("ItemAdapter.getView","aproveitou Linha");
        }


        holder.papel.setText(papel.getCodigoPapel());
        holder.valorCompra.setText(doubleFormater(papel.getValorCompra()));
        holder.valorAtual.setText(doubleFormater(papel.getPapelAtualizado().getValorAtual()));

        int idImagem = 0;
        switch (papel.getPapelAtualizado().getOscilacao()) {
            case NEGATIVO: idImagem = R.drawable.ic_indiferente; break;
            case NEUTRO: idImagem = R.drawable.ic_feliz; break;
            case POSITIVO: idImagem = R.drawable.ic_muito_feliz; break;
        }

        holder.oscilacao.setImageDrawable(this.context.getResources().getDrawable(idImagem));

      //  holder.valorizacao.setText(String.valueOf(papel.getPapelAtualizado().getValorAtual()-papel.getValorCompra()));
        holder.valorizacao.setText(doubleFormater(papel.getPapelAtualizado().getOscilacaoPrecoDia()));


        if(papel.getPapelAtualizado().getOscilacaoPrecoDia() <0)
            holder.valorizacao.setTextColor(Color.RED);
        else
            holder.valorizacao.setTextColor(Color.GREEN);



        return convertView;


    }

    private String doubleFormater(double valor)
    {
        DecimalFormat formatter = new DecimalFormat("R$ #0.00");
        return formatter.format(valor);
    }

}

class ViewHolder
{
    TextView papel;
    TextView valorCompra;
    ImageView oscilacao;
    TextView valorAtual;
    TextView valorizacao;

    public ViewHolder(View view)
    {
        this.papel = (TextView) view.findViewById(R.id.txtPapel);
        this.valorCompra = (TextView)view.findViewById(R.id.txtValorCompra);
        this.oscilacao = (ImageView) view.findViewById(R.id.imagenOscilacao);
        this.valorAtual = (TextView)view.findViewById(R.id.txtValorAtual);
        this.valorizacao = (TextView)view.findViewById(R.id.txtValorizacao);

    }

}
