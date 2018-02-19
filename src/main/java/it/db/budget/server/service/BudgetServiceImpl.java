package it.db.budget.server.service;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import it.db.budget.client.service.BudgetService;
import it.db.budget.server.retrofit.BudgetRetrofitService;
import it.db.budget.shared.bean.ProdottiEntity;

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
