package com.br.cotaacaoapp.controller;

import android.util.Log;

import com.br.cotaacaoapp.AcaoApplication;
import com.br.cotaacaoapp.task.http.AcaoGoogleTask;
import com.br.cotaacaoapp.dto.Papel;


public class AcaoController {

	public AcaoApplication application;

	public AcaoController(AcaoApplication application)
	{
		this.application = application;
	}

	public void consultarValorAcao(Papel papel)
	{
		Log.i("consultando papel ",papel.getCodigoPapel());
		new AcaoGoogleTask(this.application).execute(papel);

		//return dao.consultaValorAtual(papel);

	}


	
}
