package com.br.cotaacaoapp.dto;

import com.br.cotaacaoapp.enumerators.Oscilacao;

import java.io.Serializable;
import java.util.Date;
import java.util.Enumeration;

/**
 * 
 * @author Michel
 *Classe responsavel por manter a atualizaçao do valor de cada papel constante na carteiraController
 */
public class PapelAtualizado implements Serializable{

	private String codigoPapel;
	private double valorAtual;
	private Date horaAtualizacao;
	private double percentualDia;
	private double oscilacaoPrecoDia;
	private Oscilacao oscilacao = Oscilacao.NEUTRO;

	public String getCodigoPapel() {
		return codigoPapel;
	}

	public void setCodigoPapel(String codigoPapel) {
		this.codigoPapel = codigoPapel;
	}

	public double getValorAtual() {
		return valorAtual;
	}

	public void setValorAtual(double valorAtual) {
		this.valorAtual = valorAtual;
	}

	public Date getHoraAtualizacao() {
		return horaAtualizacao;
	}

	public void setHoraAtualizacao(Date horaAtualizacao) {
		this.horaAtualizacao = horaAtualizacao;
	}

	public double getPercentualDia() {
		return percentualDia;
	}

	public void setPercentualDia(double percentualDia) {
		this.percentualDia = percentualDia;
	}

	public double getOscilacaoPrecoDia() {
		return oscilacaoPrecoDia;
	}

	public void setOscilacaoPrecoDia(double oscilacaoPrecoDia) {
		this.oscilacaoPrecoDia = oscilacaoPrecoDia;
	}

	public Oscilacao getOscilacao() {
		return oscilacao;
	}

	public void setOscilacao(Oscilacao oscilacao) {
		this.oscilacao = oscilacao;
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
}