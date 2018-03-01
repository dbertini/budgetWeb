package it.db.budget.client.application.scontrini;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import it.db.budget.client.application.ApplicationPresenter;
import it.db.budget.client.place.NameTokens;

public class ScontriniPresenter extends Presenter<ScontriniPresenter.MyView, ScontriniPresenter.MyProxy> implements ScontriniUiHandlers {

	PlaceManager placeManager;
	
	interface MyView extends View, HasUiHandlers<ScontriniPresenter> {
		//void setDataSourceName(String aReportName);
		void cleanData();
	}
	
	@ProxyStandard
	@NameToken(NameTokens.NUOVO_SCONTINO)
	interface MyProxy extends ProxyPlace<ScontriniPresenter> {
	}
	
	@Inject
	ScontriniPresenter(EventBus eventBus, MyView view, MyProxy proxy, PlaceManager aPlaceManager) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_MAIN);
		getView().setUiHandlers(this);
		this.placeManager = aPlaceManager;
	}
	
	@Override
	public void prepareFromRequest(PlaceRequest request) {
		super.prepareFromRequest(request);
//		this.dsName = request.getParameter(ApplicationConstants.NOME_DS, "");// esempio di recupero dei parametri
//		getView().setDataSourceName(this.dsName);
	}
	
	@Override
	protected void onBind() {
		super.onBind();
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		getView().cleanData();
	}

	@Override
	public void sendToRicercaSpese() {
		PlaceRequest placeRequest = new PlaceRequest.Builder().nameToken(NameTokens.RICERCA_SPESE).build();
		placeManager.revealPlace(placeRequest);
	}
}
