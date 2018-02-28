package it.db.budget.client.application.spese;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class RicercaSpeseModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(RicercaSpesePresenter.class, RicercaSpesePresenter.MyView.class, RicercaSpeseView.class,
				RicercaSpesePresenter.MyProxy.class);
	}
}
