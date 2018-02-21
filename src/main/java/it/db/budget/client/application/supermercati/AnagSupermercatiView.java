package it.db.budget.client.application.supermercati;

import java.util.ArrayList;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Icon;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
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
import it.db.budget.shared.bean.SupermercatiEntity;

public class AnagSupermercatiView extends ViewWithUiHandlers<AnagSupermercatiPresenter> implements AnagSupermercatiPresenter.MyView {


	@UiField
	Button salvaButton;
	@UiField
	TextBox supermercatoTB;
	
	@UiField(provided = true)
    CellTable<SupermercatiEntity> cellTable = new CellTable<SupermercatiEntity>(10000);

	interface Binder extends UiBinder<Widget, AnagSupermercatiView> {
	}
	
	private ListDataProvider<SupermercatiEntity> cellTableProvider = new ListDataProvider<SupermercatiEntity>();
	
	@Inject
	AnagSupermercatiView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		final TextColumn<SupermercatiEntity> col1 = new TextColumn<SupermercatiEntity>() {

            @Override
            public String getValue(final SupermercatiEntity object) {
                return String.valueOf(object.getIdSupermercato().toString());
            }
        };
        cellTable.addColumn(col1, "Prodotto");
		
        final TextColumn<SupermercatiEntity> col2 = new TextColumn<SupermercatiEntity>() {

            @Override
            public String getValue(final SupermercatiEntity object) {
                return String.valueOf(object.getNome());
            }
        };
        cellTable.addColumn(col2, "Prodotto");
        cellTable.setStriped(true);
        cellTableProvider.addDataDisplay(cellTable);

        buildData() ;
        
		GWT.log("AnagProdottiView load ");
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Salva!");

		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.insertSupermercati(supermercatoTB.getText(), new AsyncCallback<Void>() {

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
		
		GWT.log("Dentro buildData di AnagProdottiView!");
        service.getListaSupermercati(new AsyncCallback<ArrayList<SupermercatiEntity>>() {
			
			@Override
			public void onSuccess(ArrayList<SupermercatiEntity> result) {
				cellTableProvider.getList().addAll(result);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento dei prodotti spesa.", caught);
			}
		});
	}
}
