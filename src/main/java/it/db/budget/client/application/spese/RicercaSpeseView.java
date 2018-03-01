package it.db.budget.client.application.spese;

import java.math.BigDecimal;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
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
import it.db.budget.shared.bean.SpeseResponse;

public class RicercaSpeseView extends ViewWithUiHandlers<RicercaSpesePresenter> implements RicercaSpesePresenter.MyView {


	@UiField
	Button cercaButton;
	@UiField
	TextBox dataSpesa;
	
	@UiField(provided = true)
    CellTable<SpeseResponse> cellTable = new CellTable<SpeseResponse>(10000);

	interface Binder extends UiBinder<Widget, RicercaSpeseView> {
	}
	
	private ListDataProvider<SpeseResponse> cellTableProvider = new ListDataProvider<SpeseResponse>();
	
	@Inject
	RicercaSpeseView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		final TextColumn<SpeseResponse> col1 = new TextColumn<SpeseResponse>() {

            @Override
            public String getValue(final SpeseResponse object) {
            	DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
            	return fmt.format(object.getDataSpesa());
                //return String.valueOf();
            }
        };
        cellTable.addColumn(col1, "Data Spesa");
		
        final TextColumn<SpeseResponse> col2 = new TextColumn<SpeseResponse>() {

            @Override
            public String getValue(final SpeseResponse object) {
                return String.valueOf(object.getTipoSpesaDesc());
            }
        };
        cellTable.addColumn(col2, "Tipo Spesa");
        
        final TextColumn<SpeseResponse> col3 = new TextColumn<SpeseResponse>() {

            @Override
            public String getValue(final SpeseResponse object) {
                return String.valueOf(object.getTotaleSpeso());
            }
        };
        cellTable.addColumn(col3, "Totale Speso");
        
        cellTable.setStriped(true);
        cellTableProvider.addDataDisplay(cellTable);
        buildData();
	}

	
	@UiHandler("cercaButton")
	public void onCercaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Cerca!");
		buildData();
		BudgetServiceAsync service = GWT.create(BudgetService.class);
//		service.insertSupermercati(dataSpesa.getText(), new AsyncCallback<Void>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				GWT.log("Errore durante il salvataggio del prodotto spesa.", caught);
//			}
//
//			@Override
//			public void onSuccess(Void result) {
//				cleanData();
//				buildData();
//			}
//		});
		
	}

	private void cleanData() {
		cellTableProvider.flush();
		cellTableProvider.getList().clear();
		cellTableProvider.flush();
	}
	
	private void buildData() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		
		GWT.log("Dentro buildData di RicercaSpeseView!");
        service.getListaSpese(new Long(0), new Long(0), BigDecimal.ZERO, new AsyncCallback<List<SpeseResponse>>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento della lista delle spese.", caught);
			}

			@Override
			public void onSuccess(List<SpeseResponse> result) {
				cellTableProvider.getList().addAll(result);
			}
		});

	}
}
