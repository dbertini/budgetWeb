package it.db.budget.shared.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProdottiScontrinoResponse implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -7055316364679174329L;
	private BigDecimal idScontrino;
	private BigDecimal idProdottoSpesa;
	private BigDecimal quantita;
	private BigDecimal prezzoUnitario;
	private BigDecimal percentualeSconto;
	private BigDecimal prezzoDefinitivo;
	private String prodotto;

	public BigDecimal getIdScontrino() {
		return idScontrino;
	}
	public void setIdScontrino(BigDecimal idScontrino) {
		this.idScontrino = idScontrino;
	}
	public BigDecimal getIdProdottoSpesa() {
		return idProdottoSpesa;
	}
	public void setIdProdottoSpesa(BigDecimal idProdottoSpesa) {
		this.idProdottoSpesa = idProdottoSpesa;
	}
	public BigDecimal getQuantita() {
		return quantita;
	}
	public void setQuantita(BigDecimal quantita) {
		this.quantita = quantita;
	}
	public BigDecimal getPrezzoUnitario() {
		return prezzoUnitario;
	}
	public void setPrezzoUnitario(BigDecimal prezzoUnitario) {
		this.prezzoUnitario = prezzoUnitario;
	}
	public BigDecimal getPercentualeSconto() {
		return percentualeSconto;
	}
	public void setPercentualeSconto(BigDecimal percentualeSconto) {
		this.percentualeSconto = percentualeSconto;
	}
	public BigDecimal getPrezzoDefinitivo() {
		return prezzoDefinitivo;
	}
	public void setPrezzoDefinitivo(BigDecimal prezzoDefinitivo) {
		this.prezzoDefinitivo = prezzoDefinitivo;
	}
	public String getProdotto() {
		return prodotto;
	}
	public void setProdotto(String prodotto) {
		this.prodotto = prodotto;
	}
}
