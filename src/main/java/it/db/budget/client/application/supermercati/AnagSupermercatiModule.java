package it.db.budget.client.application.supermercati;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AnagSupermercatiModule extends AbstractPresenterModule {
	@Override
	protected void configure() {
		bindPresenter(AnagSupermercatiPresenter.class, AnagSupermercatiPresenter.MyView.class, AnagSupermercatiView.class,
				AnagSupermercatiPresenter.MyProxy.class);
	}
}
