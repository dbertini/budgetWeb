package it.db.budget.client.application.scontrini;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ScontriniModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(ScontriniPresenter.class, ScontriniPresenter.MyView.class, ScontriniView.class,
				ScontriniPresenter.MyProxy.class);
	}
}
