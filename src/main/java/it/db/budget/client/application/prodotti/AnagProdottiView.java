package it.db.budget.client.application.prodotti;

import org.gwtbootstrap3.client.ui.Button;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

public class AnagProdottiView extends ViewWithUiHandlers<AnagProdottiPresenter> implements AnagProdottiPresenter.MyView {

	
	@UiField
	Button indietroButton;
	
	
	interface Binder extends UiBinder<Widget, AnagProdottiView> {
	}
	
	@Inject
	AnagProdottiView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		
		GWT.log("AnagProdottiView load ");
	}

	
	@UiHandler("indietroButton")
	public void onButtonIndietroClick(final ClickEvent event) {
		//getUiHandlers().sendToListaDataSource();
		GWT.log("Cliccato bottone!");
	}
}
