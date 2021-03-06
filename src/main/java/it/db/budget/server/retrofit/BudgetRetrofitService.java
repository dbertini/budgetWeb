package it.db.budget.server.retrofit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.db.budget.server.retrofit.interfaces.BudgetRetrofitInterface;
import it.db.budget.server.utils.ApplicationConstants;
import it.db.budget.shared.bean.CommonMessageResponse;
import it.db.budget.shared.bean.CommonNumericResponse;
import it.db.budget.shared.bean.ListaProdottiScontrinoResponse;
import it.db.budget.shared.bean.ListaSpeseResponse;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SpeseResponse;
import it.db.budget.shared.bean.SupermercatiEntity;
import it.db.budget.shared.bean.TipiSpeseEntity;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BudgetRetrofitService {

	private BudgetRetrofitInterface service;

	public BudgetRetrofitService() {
		init();
	}

	private void init() {
		String baseUrl = ApplicationConstants.BUDGET_URL;
		Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
				.build();
		this.service = retrofit.create(BudgetRetrofitInterface.class);
	}

	public ArrayList<ProdottiEntity> getListaProdottiSpesa() throws Exception {
		Call<List<ProdottiEntity>> call = service.listaProdottiSpesa();

		Response<List<ProdottiEntity>> response = call.execute();

		if (response.errorBody() != null) {
			return null;
		} else {
			List<ProdottiEntity> confs = response.body();
			return (ArrayList<ProdottiEntity>) confs;
		}
	}

	public void insertProdottoSpesa(String aProdotto) throws Exception {
		Call<CommonMessageResponse> call = service.insertProdottoSpesa(aProdotto);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}

	public ArrayList<SupermercatiEntity> getListaSupermercati() throws Exception {
		Call<List<SupermercatiEntity>> call = service.listaSupermercati();

		Response<List<SupermercatiEntity>> response = call.execute();

		if (response.errorBody() != null) {
			return null;
		} else {
			List<SupermercatiEntity> confs = response.body();
			return (ArrayList<SupermercatiEntity>) confs;
		}
	}

	public void insertSupermercati(String aSupermercato) throws Exception {
		Call<CommonMessageResponse> call = service.insertSupermercati(aSupermercato);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}

	public BigDecimal insertNuovoScontrino(Long aDataSpesa, BigDecimal aIdSupermercato) throws Exception {
		Call<CommonNumericResponse> call = service.insertNuovoScontrino(aDataSpesa, aIdSupermercato);
		Response<CommonNumericResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		} else {
			return response.body().getResult();
		}
	}

	public void addProdottoScontrino(BigDecimal idscontrino, BigDecimal idprodotto, BigDecimal quantita,
			BigDecimal prezzounitario, BigDecimal percentualesconto, BigDecimal prezzodefinitivo) throws Exception {

		Call<CommonMessageResponse> call = service.addProdottoScontrino(idscontrino, idprodotto, quantita,
				prezzounitario, percentualesconto, prezzodefinitivo);

		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	public ListaProdottiScontrinoResponse getListaProdottiScontrino(BigDecimal idscontrino) throws Exception {
		Call<ListaProdottiScontrinoResponse> call = service.getListaProdotti(idscontrino);
		Response<ListaProdottiScontrinoResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		} else {
			return response.body();
		}
	}
	
	public void editScontrino(BigDecimal idscontrino, Long dataspesa, BigDecimal idsupermercato, BigDecimal totalespeso) throws Exception {
		Call<CommonMessageResponse> call = service.editScontrino(idscontrino, dataspesa, idsupermercato, totalespeso);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	public void chiudiScontrino(BigDecimal aIdScontrino) throws Exception {
		Call<CommonMessageResponse> call = service.chiudiScontrino(aIdScontrino);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	public List<SpeseResponse> getListaSpese(Long aDaData, Long aAData, BigDecimal aTipoSpesa) throws Exception {
		
		System.err.println("dadata: " + aDaData);
		System.err.println("aAData: " + aAData);
		System.err.println("aTipoSpesa: " + aTipoSpesa);
		
		Call<ListaSpeseResponse> call = service.getListaSpese(aDaData, aAData, aTipoSpesa);
		System.err.println("Dopo la creazione della call");
		Response<ListaSpeseResponse> response = call.execute();
		System.err.println("Dopo la call");
		System.err.println("Response: " + response.toString());
		if (response.errorBody() != null) {
			System.err.println(response.errorBody().toString());
			throw new Exception(response.errorBody().toString());
		} else {
			return response.body().getSpese();
		}
	}
	
	
	public void removeProdottoScontrino(BigDecimal idscontrino, BigDecimal idprodotto) throws Exception {
		Call<CommonMessageResponse> call = service.removeProdottoScontrino(idscontrino, idprodotto);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	public ArrayList<TipiSpeseEntity> getListaTipiSpese() throws Exception {
		Call<List<TipiSpeseEntity>> call = service.getListaTipiSpese();

		Response<List<TipiSpeseEntity>> response = call.execute();

		if (response.errorBody() != null) {
			return null;
		} else {
			List<TipiSpeseEntity> confs = response.body();
			return (ArrayList<TipiSpeseEntity>) confs;
		}
	}

	public void insertTipoSpese(String aTipoSpesa) throws Exception {
		Call<CommonMessageResponse> call = service.insertTipoSpese(aTipoSpesa);
		Response<CommonMessageResponse> response = call.execute();
		if (response.errorBody() != null) {
			throw new Exception(response.errorBody().toString());
		}
	}
}
