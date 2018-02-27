package it.db.budget.client.application.scontrini;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.TextBox;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import it.db.budget.client.service.BudgetService;
import it.db.budget.client.service.BudgetServiceAsync;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.SupermercatiEntity;

public class ScontriniView extends ViewWithUiHandlers<ScontriniPresenter> implements ScontriniPresenter.MyView {

	@UiField
	Button salvaButton;
	@UiField
	TextBox dataSpesaTB;
	
	@UiField
	TextBox totaleSpeso;
	
	@UiField
	ListBox select;
	
	@UiField
	ListBox prodottiSpesa;
	@UiField
	TextBox quantitaTB;
	@UiField
	TextBox prezzoTB;
	@UiField
	TextBox scontoTB;
	@UiField
	TextBox prezzoFinaleTB;
	
	@UiField
	Container prodottiContainer;
	@UiField
	Column colbottone;

	@UiField
	Button addProduct;
	
	private BigDecimal idScontrino = null;
	
	HashMap<Integer, SupermercatiEntity> supermercatiFound = new HashMap<Integer, SupermercatiEntity>();
	HashMap<Integer, ProdottiEntity> prodottiFound = new HashMap<Integer, ProdottiEntity>();
	
	
	

	interface Binder extends UiBinder<Widget, ScontriniView> {
	}
	
	private ListDataProvider<ProdottiEntity> cellTableProvider = new ListDataProvider<ProdottiEntity>();
	
	@Inject
	ScontriniView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		buildTendinaSupermercati();
		buildTendinaProdottiSpesa();

		colbottone.setMarginTop(25);
		
		totaleSpeso.setEnabled(false);
		
		//FIXME: rimettere
		//prodottiContainer.setVisible(false);
		
		
		prezzoTB.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode()==9) {
					try {
						BigDecimal qta = new BigDecimal(quantitaTB.getValue());
						BigDecimal prezzo = new BigDecimal(prezzoTB.getValue().replaceAll(",", "."));
						BigDecimal risultato = qta.multiply(prezzo);
						prezzoFinaleTB.setValue(risultato.toString());
						prezzoFinaleTB.setText(risultato.toString());
					}catch (Exception e) {
					}
				}
			}
		});
		
		scontoTB.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(event.getNativeKeyCode()==9) {
					try {
						BigDecimal qta = new BigDecimal(quantitaTB.getValue());
						BigDecimal prezzo = new BigDecimal(prezzoTB.getValue().replaceAll(",", "."));
						BigDecimal sconto = new BigDecimal(scontoTB.getValue());
						BigDecimal risultato = qta.multiply(prezzo).setScale(2,RoundingMode.HALF_DOWN);
						BigDecimal tot_sconto = qta.multiply(prezzo).multiply(sconto).setScale(2,RoundingMode.HALF_DOWN);
						tot_sconto = tot_sconto.divide(new BigDecimal(100)).setScale(2,RoundingMode.HALF_DOWN);
						risultato = risultato.subtract(tot_sconto).setScale(2,RoundingMode.HALF_DOWN);
						prezzoFinaleTB.setValue(risultato.toString());
						prezzoFinaleTB.setText(risultato.toString());
					}catch (Exception e) {
					}
				}
			}
		});
		
		
		 
		
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		GWT.log("Cliccato bottone Salva di un nuovo scontrino");
		
		if(select.getSelectedIndex()>0 && dataSpesaTB.getText() != null) {
			SupermercatiEntity supermercatoSelected = supermercatiFound.get(select.getSelectedIndex());
			
			BudgetServiceAsync service = GWT.create(BudgetService.class);
			
			service.salvaNuovoScontrino(dataSpesaTB.getText(), supermercatoSelected.getIdSupermercato(), new AsyncCallback<BigDecimal>() {

				@Override
				public void onFailure(Throwable caught) {
					com.google.gwt.user.client.Window.alert("Errore durante il salvataggio dello scontrino. " + caught.getMessage());
				}

				@Override
				public void onSuccess(BigDecimal result) {
					idScontrino = result;
					com.google.gwt.user.client.Window.alert("Scontrino salvato correttamente.");
					
					
					prodottiContainer.setVisible(true);
					
					
				}
			});
			
		} else {
			com.google.gwt.user.client.Window.alert("Attenzione i campi Data Scontrino e Supermercato devono essere valorizzati!");
		}

	}
	
	@UiHandler("addProduct")
	public void onAddProdottoClick(final ClickEvent event) {
		
		if(prodottiSpesa.getSelectedIndex()>0 && prezzoFinaleTB.getText() != null){
			com.google.gwt.user.client.Window.alert("salvo");
		} else {
			com.google.gwt.user.client.Window.alert("Attenzione i campi Prodotto e Prezzo finale devono essere valorizzati!");
		}
		
		
			
	}
	
	
	
	
	
	private void buildTendinaSupermercati() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.getListaProdottiSpesa(new AsyncCallback<ArrayList<ProdottiEntity>>() {

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(ArrayList<ProdottiEntity> result) {
				prodottiSpesa.insertItem("Prodotto spesa...", 0);
				for (int i = 0; i < result.size(); i++) {
					prodottiSpesa.insertItem(result.get(i).getProdottoSpesa(), (i+1));
					prodottiFound.put((i+1), result.get(i));
				}
				
			}
		});
	}
	
	private void buildTendinaProdottiSpesa() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.getListaSupermercati(new AsyncCallback<ArrayList<SupermercatiEntity>>() {
			
			@Override
			public void onSuccess(ArrayList<SupermercatiEntity> result) {
				
				select.insertItem("Selezionare il supermercato...", 0);
				for (int i = 0; i < result.size(); i++) {
					select.insertItem(result.get(i).getNome(), (i+1));
					supermercatiFound.put((i+1), result.get(i));
				}
			}
			@Override
			public void onFailure(Throwable caught) {
			}
		});
	}
}
