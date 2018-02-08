package it.db.budget.client.application.prodotti;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AnagProdottiModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(AnagProdottiPresenter.class, AnagProdottiPresenter.MyView.class, AnagProdottiView.class,
				AnagProdottiPresenter.MyProxy.class);
	}
}
