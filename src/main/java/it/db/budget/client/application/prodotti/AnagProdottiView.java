package it.db.budget.client.application.prodotti;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import it.db.budget.client.application.entity.ProdottiEntity;

public class AnagProdottiView extends ViewWithUiHandlers<AnagProdottiPresenter> implements AnagProdottiPresenter.MyView {

	
	@UiField
	Button cercaButton;
	@UiField
	Button salvaButton;
	@UiField
	TextBox prodottoTB;
	
	@UiField(provided = true)
    CellTable<ProdottiEntity> cellTable = new CellTable<ProdottiEntity>(10);
	
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
        cellTable.addColumn(col1, "Prodotto");
		
        final TextColumn<ProdottiEntity> col2 = new TextColumn<ProdottiEntity>() {

            @Override
            public String getValue(final ProdottiEntity object) {
                return String.valueOf(object.getIdProdottoSpesa().toString());
            }
        };
        cellTable.addColumn(col2, "Prodotto");
        
        cellTableProvider.addDataDisplay(cellTable);
        
        
        
        ProdottiEntity prod = new ProdottiEntity();
        prod.setIdProdottoSpesa(new Long(1));
        prod.setProdottoSpesa("Pane");
        
        
        
        cellTableProvider.getList().add(prod);
        
		
		GWT.log("AnagProdottiView load ");
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		//getUiHandlers().sendToListaDataSource();
		GWT.log("Cliccato bottone Salva!");
	}
	
	@UiHandler("cercaButton")
	public void onCercaButtonClick(final ClickEvent event) {
		//getUiHandlers().sendToListaDataSource();
		GWT.log("Cliccato bottone cerca!");
	}
}
