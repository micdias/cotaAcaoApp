package com.br.cotaacaoapp.dto;

import java.util.Date;
/**
 * 
 * @author Michel
 *Classe responsavel por manter os dados de compra do papel
 */
public class Papel {
	
	
	private String descricaoPapel;
	private String codigoPapel;
	private Date dataCompra;
	private double valorCompra;
	private Date horaAtualizacao;
	private int quantidade;
	private PapelAtualizado papelAtualizado = new PapelAtualizado();
	
	public String getDescricaoPapel() {
		return descricaoPapel;
	}
	public void setDescricaoPapel(String descricaoPapel) {
		this.descricaoPapel = descricaoPapel;
	}
	public String getCodigoPapel() {
		return codigoPapel;
	}
	public void setCodigoPapel(String codigoPapel) {
		this.codigoPapel = codigoPapel;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}
	public double getValorCompra() {
		return valorCompra;
	}
	public void setValorCompra(double valorCompra) {
		this.valorCompra = valorCompra;
	}

	public Date getHoraAtualizacao() {
		return horaAtualizacao;
	}
	public void setDataAtualizacao(Date horaAtualizacao) {
		this.horaAtualizacao = horaAtualizacao;
	}


	@Override
	public boolean equals(Object obj) {
		
		if(this.codigoPapel == null)
		{
			return false;
		}
		
		return this.codigoPapel.equals(((Papel)obj).getCodigoPapel());
	}

	@Override
	public String toString() {
		return getCodigoPapel();
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public PapelAtualizado getPapelAtualizado() {
		return papelAtualizado;
	}

	public void setPapelAtualizado(PapelAtualizado papelAtualizado) {
		this.papelAtualizado = papelAtualizado;
	}


}
