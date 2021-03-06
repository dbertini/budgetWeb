package it.db.budget.server.retrofit.interfaces;

import java.math.BigDecimal;
import java.util.List;

import it.db.budget.shared.bean.CommonMessageResponse;
import it.db.budget.shared.bean.CommonNumericResponse;
import it.db.budget.shared.bean.ListaProdottiScontrinoResponse;
import it.db.budget.shared.bean.ListaSpeseResponse;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;
import it.db.budget.shared.bean.TipiSpeseEntity;
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
	Call<CommonNumericResponse> insertNuovoScontrino(@Query("dataspesa") Long dataspesa,
			@Query("idsupermercato") BigDecimal idsupermercato);

	@POST("spese/scontrini/addprodotto")
	Call<CommonMessageResponse> addProdottoScontrino(@Query("idscontrino") BigDecimal idscontrino,
			@Query("idprodotto") BigDecimal idprodotto, @Query("quantita") BigDecimal quantita,
			@Query("prezzounitario") BigDecimal prezzounitario,
			@Query("percentualesconto") BigDecimal percentualesconto,
			@Query("prezzodefinitivo") BigDecimal prezzodefinitivo);

	@GET("spese/scontrini/listaprodotti")
	Call<ListaProdottiScontrinoResponse> getListaProdotti(@Query("idscontrino") BigDecimal idscontrino);

	@POST("spese/scontrini/edit")
	Call<CommonMessageResponse> editScontrino(@Query("idscontrino") BigDecimal idscontrino,
			@Query("dataspesa") Long dataspesa, @Query("idsupermercato") BigDecimal idsupermercato,
			@Query("totalespeso") BigDecimal totalespeso);
	
	@POST("spese/scontrini/chiudiscontrino")
	Call<CommonMessageResponse> chiudiScontrino(@Query("idscontrino") BigDecimal idscontrino);
	
	@GET("spese/spesa/listaspese")
	Call<ListaSpeseResponse> getListaSpese(@Query("dadata") Long dadata, @Query("adata") Long adata, @Query("tipospesa") BigDecimal tipospesa);

	@POST("spese/scontrini/removeprodotto")
	Call<CommonMessageResponse> removeProdottoScontrino(@Query("idscontrino") BigDecimal idscontrino, @Query("idprodotto") BigDecimal idprodotto);

	@GET("anag/tipspese/list")
	Call<List<TipiSpeseEntity>> getListaTipiSpese();

	@POST("anag/tipspese/insert")
	Call<CommonMessageResponse> insertTipoSpese(@Query("nome") String nome);

}