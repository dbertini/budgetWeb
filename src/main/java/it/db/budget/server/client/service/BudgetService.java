package it.db.budget.server.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.db.budget.client.application.entity.ProdottiEntity;

@RemoteServiceRelativePath("wrservice")
public interface BudgetService extends RemoteService {

	ArrayList<ProdottiEntity> getListaProdottiSpesa();

}
