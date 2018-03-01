package it.db.budget.shared.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class ListaSpeseResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7537859119714644518L;
	
	private ArrayList<SpeseResponse> spese;
	
	public ListaSpeseResponse() {
		this.spese = new ArrayList<>();
	}
	
	public ArrayList<SpeseResponse> getSpese() {
		return spese;
	}
	public void setSpese(ArrayList<SpeseResponse> spese) {
		this.spese = spese;
	}
}
