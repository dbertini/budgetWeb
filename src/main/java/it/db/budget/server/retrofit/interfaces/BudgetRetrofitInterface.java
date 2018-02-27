package it.db.budget.server.retrofit.interfaces;

import java.math.BigDecimal;
import java.util.List;

import it.db.budget.shared.bean.CommonMessageResponse;
import it.db.budget.shared.bean.CommonNumericResponse;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BudgetRetrofitInterface {
	@GET("anag/prodottispesa/list")
	Call<List<ProdottiEntity>> listaProdottiSpesa();

	@POST("anag/prodottispesa/insert")
	Call<CommonMessageResponse> insertProdottoSpesa(@Query("nome") String nome);

	@GET("anag/supermercati/list")
	Call<List<SupermercatiEntity>> listaSupermercati();

	@POST("anag/supermercati/insert")
	Call<CommonMessageResponse> insertSupermercati(@Query("nome") String nome);

	@POST("spese/scontrini/insert")
	Call<CommonNumericResponse> insertNuovoScontrino(@Query("dataspesa") Long dataspesa, @Query("idsupermercato") BigDecimal idsupermercato);
}