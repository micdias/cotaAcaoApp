package com.br.cotaacaoapp;

import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;

/**
 * Created by Michel on 11/09/16.
 */
public interface AtualizaAcaoDelegate {
    public AcaoApplication getAcaoApplication();
    public void atualizaPapel(PapelAtualizado papel);
}
