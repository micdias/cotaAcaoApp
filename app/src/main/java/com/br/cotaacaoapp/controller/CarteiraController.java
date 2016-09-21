package com.br.cotaacaoapp.controller;

import com.br.cotaacaoapp.AcaoApplication;
import com.br.cotaacaoapp.dao.CarteiraDAO;
import com.br.cotaacaoapp.dto.Carteira;
import com.br.cotaacaoapp.dto.Papel;

import java.util.ArrayList;


public class CarteiraController {

	private CarteiraDAO carteiraDAO = null;
	private AcaoApplication application;
    private AcaoController controlAcao;


    public CarteiraController(AcaoApplication application)
	{
		this.application = application;
		carteiraDAO = CarteiraDAO.getInstance(application);
        controlAcao = new AcaoController(application);

        if(getCarteira() == null)
			setCarteira(carteiraDAO.carregarCarteira());

	}




	public boolean inserirPapel(Papel papel)
	{
	

        if(carteiraDAO.inserirPapel(papel)) {
			getCarteira().inserirItemCarteira(papel);
			controlAcao.consultarValorAcao(papel);
        }

        return true;

    }
	
	public boolean removerPapel(Papel papel)
	{
		return getCarteira().removerItemCarteira(papel);
	}
	
	public void atualizarValorAcao()
	{
		for (Papel papel : getCarteira().getPapeis()) {
			controlAcao.consultarValorAcao(papel);

		}
	}
	
	public void calculaDiferencaAcao()
	{
		
		for (Papel papel : getCarteira().getPapeis()) {
			System.out.println("Compra= "+ papel.getValorCompra()+" Valor Atual= "+ papel.getPapelAtualizado().getValorAtual()
			+" Diferenca= "+ ((papel.getPapelAtualizado().getValorAtual() - papel.getValorCompra())) +
			"Percentual "+ ((papel.getPapelAtualizado().getValorAtual() - papel.getValorCompra()/papel.getValorCompra()))+"%"
			
					);
		}
		
	}



	public ArrayList<Papel> getPapeis()
	{
		return getCarteira().getPapeis();
	}


	public Carteira getCarteira() {
		return application.getCarteira();
	}

	public void setCarteira(Carteira carteira) {
		this.application.setCarteira(carteira);
	}

	public void apagarTudo() {
		carteiraDAO.apagarTudo();
		this.application.getCarteira().getPapeis().clear();

	}
}
