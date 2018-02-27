package it.db.budget.server.retrofit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import it.db.budget.server.retrofit.interfaces.BudgetRetrofitInterface;
import it.db.budget.server.utils.ApplicationConstants;
import it.db.budget.shared.bean.CommonMessageResponse;
import it.db.budget.shared.bean.CommonNumericResponse;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;
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
		Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
		this.service = retrofit.create(BudgetRetrofitInterface.class);
	}
	
	public ArrayList<ProdottiEntity> getListaProdottiSpesa() throws Exception {
		Call<List<ProdottiEntity>> call = service.listaProdottiSpesa();
		
		Response<List<ProdottiEntity>> response = call.execute();
		
		if(response.errorBody()!=null){
			return null;
		} else{
			List<ProdottiEntity> confs = response.body();
			return (ArrayList<ProdottiEntity>)confs;
		}
	}
	
	
	public void insertProdottoSpesa(String aProdotto) throws Exception {
		Call<CommonMessageResponse> call = service.insertProdottoSpesa(aProdotto);
		Response<CommonMessageResponse> response = call.execute();
		if(response.errorBody()!=null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	
	
	public ArrayList<SupermercatiEntity> getListaSupermercati() throws Exception {
		Call<List<SupermercatiEntity>> call = service.listaSupermercati();
		
		Response<List<SupermercatiEntity>> response = call.execute();
		
		if(response.errorBody()!=null){
			return null;
		} else{
			List<SupermercatiEntity> confs = response.body();
			return (ArrayList<SupermercatiEntity>)confs;
		}
	}
	
	
	public void insertSupermercati(String aSupermercato) throws Exception {
		Call<CommonMessageResponse> call = service.insertSupermercati(aSupermercato);
		Response<CommonMessageResponse> response = call.execute();
		if(response.errorBody()!=null) {
			throw new Exception(response.errorBody().toString());
		}
	}
	
	public BigDecimal insertNuovoScontrino(Long aDataSpesa, BigDecimal aIdSupermercato) throws Exception {
		Call<CommonNumericResponse> call = service.insertNuovoScontrino(aDataSpesa, aIdSupermercato);
		Response<CommonNumericResponse> response = call.execute();
		if(response.errorBody()!=null) {
			throw new Exception(response.errorBody().toString());
		} else {
			return response.body().getResult();
		}
	}

}
