package it.db.budget.server.retrofit;

import java.util.ArrayList;
import java.util.List;

import it.db.budget.server.retrofit.interfaces.BudgetRetrofitInterface;
import it.db.budget.shared.bean.ProdottiEntity;
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
		String baseUrl = "";
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
}
