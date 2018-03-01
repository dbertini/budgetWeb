package it.db.budget.client.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.ProdottiScontrinoResponse;
import it.db.budget.shared.bean.SpeseResponse;
import it.db.budget.shared.bean.SupermercatiEntity;
import it.db.budget.shared.bean.TipiSpeseEntity;

@RemoteServiceRelativePath("wrservice")
public interface BudgetService extends RemoteService {

	public String getMessaggio();
	
	ArrayList<ProdottiEntity> getListaProdottiSpesa();
	void insertProdottoSpesa(String aProdotto) throws Exception;

	ArrayList<SupermercatiEntity> getListaSupermercati();
	void insertSupermercati(String aSupermercato) throws Exception;
	
	BigDecimal salvaNuovoScontrino(String aDataScontrino, BigDecimal aIdSupermercato) throws Exception;

	void addProdottoScontrino(BigDecimal aIdScontrino, BigDecimal aIdProdotto, BigDecimal aQuantita,
			 BigDecimal aPrezzoUnitario, BigDecimal aPercentualeSconto, BigDecimal aPrezzoDefinitivo) throws Exception;
	
	List<ProdottiScontrinoResponse> getListaProdottiScontrino(BigDecimal aIdScontrino) throws Exception;
	void editScontrino(BigDecimal aIdScontrino, String aDataScontrino, BigDecimal aIdSupermercato, BigDecimal aTotaleSpeso) throws Exception;

	void chiudiScontrino(BigDecimal aIdScontrino) throws Exception;
	List<SpeseResponse> getListaSpese(Long aDaData, Long aAData, BigDecimal aTipoSpesa) throws Exception;
	void removeProdottoScontrino(BigDecimal aIdScontrino, BigDecimal aIdProdotto) throws Exception;

	ArrayList<TipiSpeseEntity> getListaTipiSpese();
	void insertTipoSpese(String aTipoSpese) throws Exception;
}
