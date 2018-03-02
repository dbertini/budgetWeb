package it.db.budget.client.application.spese;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ListBox;
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
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SpeseResponse;
import it.db.budget.shared.bean.TipiSpeseEntity;

public class RicercaSpeseView extends ViewWithUiHandlers<RicercaSpesePresenter> implements RicercaSpesePresenter.MyView {


	@UiField
	Button cercaButton;
	@UiField
	TextBox daDataSpesa;
	
	@UiField
	TextBox aDataSpesa;
	
	@UiField
	ListBox tipiSpesaList;
	
	@UiField(provided = true)
    CellTable<SpeseResponse> cellTable = new CellTable<SpeseResponse>(10000);

	HashMap<Integer, TipiSpeseEntity> tipiSpesaFound = new HashMap<Integer, TipiSpeseEntity>();
	
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
        
        buildTendinaTipiSpese();
        
        buildData();
	}

	
	@UiHandler("cercaButton")
	public void onCercaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Cerca!");
		cleanData();
		buildData();
	}

	private void cleanData() {
		cellTableProvider.flush();
		cellTableProvider.getList().clear();
		cellTableProvider.flush();
	}
	
	private void buildData() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		
		DateTimeFormat fmt = DateTimeFormat.getFormat("dd/MM/yyyy");
		
		Long lDaDataSpesa = new Long(0);
		if(daDataSpesa.getValue() != null) {
			try {
				lDaDataSpesa = fmt.parse(daDataSpesa.getValue()).getTime();
			} catch(Exception a) {
				lDaDataSpesa = new Long(0);
			}
		}
		
		Long lADataSpesa = new Long(0);
		if(aDataSpesa.getValue() != null) {
			try {
				lADataSpesa = fmt.parse(aDataSpesa.getValue()).getTime();
			} catch(Exception a) {
				lADataSpesa = new Long(0);
			}
		}
		
		BigDecimal tipoSpesaSelected = BigDecimal.ZERO;
		if(tipiSpesaList.getSelectedIndex() > 0) {
			tipoSpesaSelected = tipiSpesaFound.get(tipiSpesaList.getSelectedIndex()).getIdTipoSpesa();
		}

		service.getListaSpese(lDaDataSpesa, lADataSpesa, tipoSpesaSelected, new AsyncCallback<List<SpeseResponse>>() {

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
	
	
	private void buildTendinaTipiSpese() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.getListaTipiSpese(new AsyncCallback<ArrayList<TipiSpeseEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento della tendina dei Tipi Spesa");
			}

			@Override
			public void onSuccess(ArrayList<TipiSpeseEntity> result) {
				tipiSpesaList.insertItem("Selezionare un tipo spesa...", 0);
				
				for (int i = 0; i < result.size(); i++) {
					tipiSpesaList.insertItem(result.get(i).getTipoSpesa(), (i+1));
					tipiSpesaFound.put((i+1), result.get(i));
				}
			}
		});
	}
}
