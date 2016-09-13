package com.br.cotaacaoapp.controller;

import android.content.Context;

import com.br.cotaacaoapp.AcaoApplication;
import com.br.cotaacaoapp.dao.CarteiraDAO;
import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;
import com.br.cotaacaoapp.dto.PapelAtualizado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class CarteiraController {

	private CarteiraDAO carteiraDAO = null;
	private Carteira carteira = null;
	private AcaoApplication application;

	public CarteiraController(AcaoApplication application)
	{
		this.application = application;
		carteiraDAO = CarteiraDAO.getInstance(application);

		if(carteira == null)
			carteira = carteiraDAO.carregarCarteira();

		//atualizarValorAcao();

	}




	public boolean inserirPapel(Papel papel)
	{
	
		carteira.inserirItemCarteira(papel);
		return true;
	}
	
	public boolean removerPapel(Papel papel)
	{
		return carteira.removerItemCarteira(papel);
	}
	
	public void atualizarValorAcao()
	{
		AcaoController controlAcao = new AcaoController(application);
		for (Papel papel : carteira.getPapeis()) {
			controlAcao.consultarValorAcao(papel);

		}
	}
	
	public void calculaDiferencaAcao()
	{
		
		for (Papel papel : carteira.getPapeis()) {
			System.out.println("Compra= "+ papel.getValorCompra()+" Valor Atual= "+ papel.getPapelAtualizado().getValorAtual()
			+" Diferenca= "+ ((papel.getPapelAtualizado().getValorAtual() - papel.getValorCompra())) +
			"Percentual "+ ((papel.getPapelAtualizado().getValorAtual() - papel.getValorCompra()/papel.getValorCompra()))+"%"
			
					);
		}
		
	}



	public ArrayList<Papel> getPapeis()
	{
		return carteira.getPapeis();
	}
}
