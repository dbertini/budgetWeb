package it.db.budget.shared.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class SupermercatiEntity implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -960983282247980687L;
	private BigDecimal idSupermercato;
	private String nome;
	
	public BigDecimal getIdSupermercato() {
		return idSupermercato;
	}
	public void setIdSupermercato(BigDecimal idSupermercato) {
		this.idSupermercato = idSupermercato;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

}
