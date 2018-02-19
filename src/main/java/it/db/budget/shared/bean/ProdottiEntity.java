package it.db.budget.shared.bean;

import java.io.Serializable;

public class ProdottiEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4668193102790870326L;
	
	private Long idProdottoSpesa;
	private String prodottoSpesa;
	public Long getIdProdottoSpesa() {
		return idProdottoSpesa;
	}
	public void setIdProdottoSpesa(Long idProdottoSpesa) {
		this.idProdottoSpesa = idProdottoSpesa;
	}
	public String getProdottoSpesa() {
		return prodottoSpesa;
	}
	public void setProdottoSpesa(String prodottoSpesa) {
		this.prodottoSpesa = prodottoSpesa;
	}

}
