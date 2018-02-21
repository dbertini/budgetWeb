package it.db.budget.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.db.budget.shared.bean.ProdottiEntity;

@RemoteServiceRelativePath("wrservice")
public interface BudgetService extends RemoteService {

	ArrayList<ProdottiEntity> getListaProdottiSpesa();
	
	public String getMessaggio();

}
