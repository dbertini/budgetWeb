package it.db.budget.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;

public interface BudgetServiceAsync {
	void getMessaggio(AsyncCallback<String> callback);
	void getListaProdottiSpesa(AsyncCallback<ArrayList<ProdottiEntity>> callback);
	void insertProdottoSpesa(String aProdotto,AsyncCallback<Void> callback);
	void getListaSupermercati(AsyncCallback<ArrayList<SupermercatiEntity>> callback);
	void insertSupermercati(String aSupermercato,AsyncCallback<Void> callback);
}
