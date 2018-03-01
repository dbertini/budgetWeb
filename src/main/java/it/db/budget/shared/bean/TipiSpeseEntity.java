package it.db.budget.shared.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TipiSpeseEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7981446631910313912L;
	private BigDecimal idTipoSpesa;
	private String tipoSpesa;
	/**
	 * @return the idTipoSpesa
	 */
	public BigDecimal getIdTipoSpesa() {
		return idTipoSpesa;
	}
	/**
	 * @param idTipoSpesa the idTipoSpesa to set
	 */
	public void setIdTipoSpesa(BigDecimal idTipoSpesa) {
		this.idTipoSpesa = idTipoSpesa;
	}
	/**
	 * @return the tipoSpesa
	 */
	public String getTipoSpesa() {
		return tipoSpesa;
	}
	/**
	 * @param tipoSpesa the tipoSpesa to set
	 */
	public void setTipoSpesa(String tipoSpesa) {
		this.tipoSpesa = tipoSpesa;
	}
	
}
