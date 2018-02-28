package it.db.budget.server.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import it.db.budget.client.service.BudgetService;
import it.db.budget.server.retrofit.BudgetRetrofitService;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.ProdottiScontrinoResponse;
import it.db.budget.shared.bean.SpeseResponse;
import it.db.budget.shared.bean.SupermercatiEntity;

public class BudgetServiceImpl extends RemoteServiceServlet implements BudgetService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2568327084740898654L;

	@Override
	public String getMessaggio() {
		// TODO Auto-generated method stub
		return "Messaggio recuperato da server!!!!!!!!!!";
	}

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

	@Override
	public void insertProdottoSpesa(String aProdotto) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		budegetservice.insertProdottoSpesa(aProdotto);
	}

	@Override
	public ArrayList<SupermercatiEntity> getListaSupermercati() {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		try {
			return budegetservice.getListaSupermercati();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}

	@Override
	public void insertSupermercati(String aSupermercato) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		budegetservice.insertSupermercati(aSupermercato);
	}

	@Override
	public BigDecimal salvaNuovoScontrino(String aDataScontrino, BigDecimal aIdSupermercato) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		long dataScontrino = fmt.parse(aDataScontrino).getTime();
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		return budegetservice.insertNuovoScontrino(dataScontrino, aIdSupermercato);
	}

	@Override
	public void addProdottoScontrino(BigDecimal aIdScontrino, BigDecimal aIdProdotto, BigDecimal aQuantita,
			BigDecimal aPrezzoUnitario, BigDecimal aPercentualeSconto, BigDecimal aPrezzoDefinitivo) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		budegetservice.addProdottoScontrino(aIdScontrino, aIdProdotto, aQuantita, aPrezzoUnitario, aPercentualeSconto,
				aPrezzoDefinitivo);
	}
	
	@Override
	public List<ProdottiScontrinoResponse> getListaProdottiScontrino(BigDecimal aIdScontrino) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		return budegetservice.getListaProdottiScontrino(aIdScontrino).getProdotti();
	}
	
	@Override
	public void editScontrino(BigDecimal aIdScontrino, String aDataScontrino, BigDecimal aIdSupermercato, BigDecimal aTotaleSpeso) throws Exception {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
		long dataScontrino = fmt.parse(aDataScontrino).getTime();
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		budegetservice.editScontrino(aIdScontrino, dataScontrino, aIdSupermercato, aTotaleSpeso);
	}

	@Override
	public void chiudiScontrino(BigDecimal aIdScontrino) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		budegetservice.chiudiScontrino(aIdScontrino);
	}

	@Override
	public List<SpeseResponse> getListaSpese(Long aDaData, Long aAData, BigDecimal aTipoSpesa) throws Exception {
		BudgetRetrofitService budegetservice = new BudgetRetrofitService();
		return budegetservice.getListaSpese(aDaData, aAData, aTipoSpesa);
	}

}
