package it.db.budget.server.retrofit.interfaces;

import java.util.List;

import it.db.budget.client.application.entity.ProdottiEntity;
import retrofit2.Call;
import retrofit2.http.GET;

public interface BudgetRetrofitInterface {
	@GET("anag/prodottispesa/list")
	Call<List<ProdottiEntity>> listaProdottiSpesa();
}
