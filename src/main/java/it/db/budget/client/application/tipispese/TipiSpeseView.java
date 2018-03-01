package it.db.budget.client.application.tipispese;

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
import it.db.budget.shared.bean.TipiSpeseEntity;

public class TipiSpeseView extends ViewWithUiHandlers<TipiSpesePresenter> implements TipiSpesePresenter.MyView {

	
//	@UiField
//	Button cercaButton;
	@UiField
	Button salvaButton;
	@UiField
	TextBox tipoSpeseTB;
	
	@UiField(provided = true)
    CellTable<TipiSpeseEntity> cellTable = new CellTable<TipiSpeseEntity>(10000);
	
	interface Binder extends UiBinder<Widget, TipiSpeseView> {
	}
	
	private ListDataProvider<TipiSpeseEntity> cellTableProvider = new ListDataProvider<TipiSpeseEntity>();
	
	@Inject
	TipiSpeseView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		final TextColumn<TipiSpeseEntity> col1 = new TextColumn<TipiSpeseEntity>() {

            @Override
            public String getValue(final TipiSpeseEntity object) {
                return String.valueOf(object.getIdTipoSpesa().toString());
            }
        };
        cellTable.addColumn(col1, "ID");
		
        final TextColumn<TipiSpeseEntity> col2 = new TextColumn<TipiSpeseEntity>() {

            @Override
            public String getValue(final TipiSpeseEntity object) {
                return String.valueOf(object.getTipoSpesa());
            }
        };
        cellTable.addColumn(col2, "Tipo Spesa");
        
        cellTableProvider.addDataDisplay(cellTable);
        
        buildData() ;
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Salva!");
		
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.insertTipoSpese(tipoSpeseTB.getText(), new AsyncCallback<Void>() {

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
	

	private void cleanData() {
		cellTableProvider.flush();
		cellTableProvider.getList().clear();
		cellTableProvider.flush();
	}
	
	private void buildData() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
        service.getListaTipiSpese(new AsyncCallback<ArrayList<TipiSpeseEntity>>() {
			
			@Override
			public void onSuccess(ArrayList<TipiSpeseEntity> result) {
				cellTableProvider.getList().addAll(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento dei prodotti spesa.", caught);
			}
		});
	}
}
