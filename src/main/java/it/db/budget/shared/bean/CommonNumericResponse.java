package it.db.budget.shared.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CommonNumericResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4129464931710007754L;

	private BigDecimal result;

	public BigDecimal getResult() {
		return result;
	}

	public void setResult(BigDecimal result) {
		this.result = result;
	}

}
