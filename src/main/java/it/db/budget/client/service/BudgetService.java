package it.db.budget.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;

@RemoteServiceRelativePath("wrservice")
public interface BudgetService extends RemoteService {

	public String getMessaggio();
	
	ArrayList<ProdottiEntity> getListaProdottiSpesa();
	void insertProdottoSpesa(String aProdotto) throws Exception;

	ArrayList<SupermercatiEntity> getListaSupermercati();
	void insertSupermercati(String aSupermercato) throws Exception;

}
