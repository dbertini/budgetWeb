package it.db.budget.client.application;

import it.db.budget.client.application.home.HomeModule;
import it.db.budget.client.application.prodotti.AnagProdottiModule;
import it.db.budget.client.application.scontrini.ScontriniModule;
import it.db.budget.client.application.spese.RicercaSpeseModule;
import it.db.budget.client.application.supermercati.AnagSupermercatiModule;
import it.db.budget.client.application.tipispese.TipiSpeseModule;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new HomeModule());
        install(new AnagProdottiModule());
        install(new AnagSupermercatiModule());
        install(new ScontriniModule());
        install(new RicercaSpeseModule());
        install(new TipiSpeseModule());
        

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class, ApplicationPresenter.MyProxy.class);
    }
}
