package it.db.budget.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import it.db.budget.client.application.entity.ProdottiEntity;
import it.db.budget.server.client.service.BudgetService;
import it.db.budget.server.retrofit.BudgetRetrofitService;

public class BudgetServiceImpl extends RemoteServiceServlet implements BudgetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2568327084740898654L;

	@Override
	public ArrayList<ProdottiEntity> getListaProdottiSpesa() {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		try {
			return budegetservice.getListaProdottiSpesa();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
}
