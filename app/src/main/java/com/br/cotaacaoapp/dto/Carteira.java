package com.br.cotaacaoapp.dto;

import java.util.ArrayList;

public class Carteira {
	private String descricao;
	private ArrayList<Papel> listPapeis = new ArrayList<Papel>();

	public void inserirItemCarteira(Papel papel)
	{
		getListPapeis().add(papel);
	}
	
	public boolean removerItemCarteira(Papel papel)
	{
		return getListPapeis().remove(papel);
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Papel> getPapeis()
	{
		return getListPapeis();
	}


	public ArrayList<Papel> getListPapeis() {
		return listPapeis;
	}

	public void setListPapeis(ArrayList<Papel> listPapeis) {
		this.listPapeis = listPapeis;
	}
}
