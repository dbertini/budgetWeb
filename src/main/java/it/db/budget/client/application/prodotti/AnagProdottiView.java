package it.db.budget.client.application.prodotti;

import java.util.ArrayList;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import it.db.budget.client.service.BudgetService;
import it.db.budget.client.service.BudgetServiceAsync;
import it.db.budget.shared.bean.ProdottiEntity;

public class AnagProdottiView extends ViewWithUiHandlers<AnagProdottiPresenter> implements AnagProdottiPresenter.MyView {

	
//	@UiField
//	Button cercaButton;
	@UiField
	Button salvaButton;
	@UiField
	TextBox prodottoTB;
	
	@UiField(provided = true)
    CellTable<ProdottiEntity> cellTable = new CellTable<ProdottiEntity>(10000);
	
	interface Binder extends UiBinder<Widget, AnagProdottiView> {
	}
	
	private ListDataProvider<ProdottiEntity> cellTableProvider = new ListDataProvider<ProdottiEntity>();
	
	@Inject
	AnagProdottiView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		final TextColumn<ProdottiEntity> col1 = new TextColumn<ProdottiEntity>() {

            @Override
            public String getValue(final ProdottiEntity object) {
                return String.valueOf(object.getIdProdottoSpesa().toString());
            }
        };
        cellTable.addColumn(col1, "ID");
		
        final TextColumn<ProdottiEntity> col2 = new TextColumn<ProdottiEntity>() {

            @Override
            public String getValue(final ProdottiEntity object) {
                return String.valueOf(object.getProdottoSpesa());
            }
        };
        cellTable.addColumn(col2, "Prodotto");
        
        cellTableProvider.addDataDisplay(cellTable);
        
        buildData() ;
        
		
		GWT.log("AnagProdottiView load ");
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Salva!");
		
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.insertProdottoSpesa(prodottoTB.getText(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il salvataggio del prodotto spesa.", caught);
			}

			@Override
			public void onSuccess(Void result) {
				cleanData();
				buildData();
			}
		});
		
	}
	
//	@UiHandler("cercaButton")
//	public void onCercaButtonClick(final ClickEvent event) {
//		//getUiHandlers().sendToListaDataSource();
//		
//		
//		
//		
//		GWT.log("Cliccato bottone cerca!");
//	}
	
	private void cleanData() {
		cellTableProvider.flush();
		cellTableProvider.getList().clear();
		cellTableProvider.flush();
	}
	
	private void buildData() {
		//FIXME: mettere la giusta entity di shared!
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		
		GWT.log("Dentro buildData di AnagProdottiView!");

        service.getListaProdottiSpesa(new AsyncCallback<ArrayList<ProdottiEntity>>() {
			
			@Override
			public void onSuccess(ArrayList<ProdottiEntity> result) {
				cellTableProvider.getList().addAll(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento dei prodotti spesa.", caught);
			}
		});
	}
}
