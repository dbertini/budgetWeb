package it.db.budget.client.application;

import it.db.budget.client.application.home.HomeModule;
import it.db.budget.client.application.prodotti.AnagProdottiModule;
import it.db.budget.client.application.scontrini.ScontriniModule;
import it.db.budget.client.application.supermercati.AnagSupermercatiModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new AnagProdottiModule());
        install(new AnagSupermercatiModule());
        install(new ScontriniModule());
        

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
