package it.db.budget.shared.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SpeseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5455715526156596893L;

	private BigDecimal idSpesa;
	private BigDecimal tipoSpesa;
	private Date dataSpesa;
	private BigDecimal totaleSpeso;
	private String tipoSpesaDesc;
	

	public BigDecimal getIdSpesa() {
		return idSpesa;
	}
	public void setIdSpesa(BigDecimal idSpesa) {
		this.idSpesa = idSpesa;
	}
	public BigDecimal getTipoSpesa() {
		return tipoSpesa;
	}
	public void setTipoSpesa(BigDecimal tipoSpesa) {
		this.tipoSpesa = tipoSpesa;
	}
	public Date getDataSpesa() {
		return dataSpesa;
	}
	public void setDataSpesa(Date dataSpesa) {
		this.dataSpesa = dataSpesa;
	}
	public BigDecimal getTotaleSpeso() {
		return totaleSpeso;
	}
	public void setTotaleSpeso(BigDecimal totaleSpeso) {
		this.totaleSpeso = totaleSpeso;
	}
	public String getTipoSpesaDesc() {
		return tipoSpesaDesc;
	}
	public void setTipoSpesaDesc(String tipoSpesaDesc) {
		this.tipoSpesaDesc = tipoSpesaDesc;
	}
}
