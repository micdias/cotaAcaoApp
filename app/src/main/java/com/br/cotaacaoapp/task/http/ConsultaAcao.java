package com.br.cotaacaoapp.task.http;

/**
 * Created by Michel on 04/09/16.
 */

import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;


public interface ConsultaAcao  {

    public PapelAtualizado consultaValorAtual(Papel papel);

}
