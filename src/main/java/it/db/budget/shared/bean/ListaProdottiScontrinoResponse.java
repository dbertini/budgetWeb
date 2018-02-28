package it.db.budget.shared.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaProdottiScontrinoResponse implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3881652147989852136L;

	private List<ProdottiScontrinoResponse> prodotti;
	
	public ListaProdottiScontrinoResponse() {
		this.prodotti = new ArrayList<>();
	}

	public List<ProdottiScontrinoResponse> getProdotti() {
		return prodotti;
	}

	public void setProdotti(List<ProdottiScontrinoResponse> prodotti) {
		this.prodotti = prodotti;
	}

}
