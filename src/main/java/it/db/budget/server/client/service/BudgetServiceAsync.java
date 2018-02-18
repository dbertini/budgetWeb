package it.db.budget.server.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import it.db.budget.client.application.entity.ProdottiEntity;

public interface BudgetServiceAsync {
	void getListaProdottiSpesa(AsyncCallback<ArrayList<ProdottiEntity>> callback);
}
