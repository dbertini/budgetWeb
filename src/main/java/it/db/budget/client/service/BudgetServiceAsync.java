package it.db.budget.client.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.ProdottiScontrinoResponse;
import it.db.budget.shared.bean.SpeseResponse;
import it.db.budget.shared.bean.SupermercatiEntity;

public interface BudgetServiceAsync {
	void getMessaggio(AsyncCallback<String> callback);
	void getListaProdottiSpesa(AsyncCallback<ArrayList<ProdottiEntity>> callback);
	void insertProdottoSpesa(String aProdotto,AsyncCallback<Void> callback);
	void getListaSupermercati(AsyncCallback<ArrayList<SupermercatiEntity>> callback);
	void insertSupermercati(String aSupermercato,AsyncCallback<Void> callback);
	void salvaNuovoScontrino(String aDataScontrino, BigDecimal aIdSupermercato, AsyncCallback<BigDecimal> callback);
	void addProdottoScontrino(BigDecimal aIdScontrino, BigDecimal aIdProdotto, BigDecimal aQuantita,
			 BigDecimal aPrezzoUnitario, BigDecimal aPercentualeSconto, BigDecimal aPrezzoDefinitivo,AsyncCallback<Void> callback);
	void getListaProdottiScontrino(BigDecimal aIdScontrino, AsyncCallback<List<ProdottiScontrinoResponse>> callback);
	void editScontrino(BigDecimal aIdScontrino, String aDataScontrino, BigDecimal aIdSupermercato, BigDecimal aTotaleSpeso, AsyncCallback<Void> callback);
	void chiudiScontrino(BigDecimal aIdScontrino, AsyncCallback<Void> callback); 
	void getListaSpese(Long aDaData, Long aAData, BigDecimal aTipoSpesa, AsyncCallback<List<SpeseResponse>> callback);
	void removeProdottoScontrino(BigDecimal aIdScontrino, BigDecimal aIdProdotto, AsyncCallback<Void> callback);
}
