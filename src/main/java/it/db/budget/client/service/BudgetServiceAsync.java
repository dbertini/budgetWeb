package it.db.budget.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import it.db.budget.shared.bean.ProdottiEntity;

public interface BudgetServiceAsync {
	void getListaProdottiSpesa(AsyncCallback<ArrayList<ProdottiEntity>> callback);
	void getMessaggio(AsyncCallback<String> callback);
}
