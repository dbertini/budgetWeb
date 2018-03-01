package it.db.budget.client.application.tipispese;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TipiSpeseModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(TipiSpesePresenter.class, TipiSpesePresenter.MyView.class, TipiSpeseView.class,
				TipiSpesePresenter.MyProxy.class);
	}
}
