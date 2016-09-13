package com.br.cotaacaoapp.dto;

import java.util.ArrayList;
import java.util.List;

public class Carteira {
	private String descricao;
	ArrayList<Papel> carteira = new ArrayList<Papel>();

	public void inserirItemCarteira(Papel papel)
	{
		carteira.add(papel);
	}
	
	public boolean removerItemCarteira(Papel papel)
	{
		return carteira.remove(papel);
		
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<Papel> getPapeis()
	{
		return carteira;
	}


}
