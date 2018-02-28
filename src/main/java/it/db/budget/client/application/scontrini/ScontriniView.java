package it.db.budget.client.application.scontrini;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Column;
import org.gwtbootstrap3.client.ui.Container;
import org.gwtbootstrap3.client.ui.ListBox;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.gwt.ButtonCell;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import it.db.budget.client.service.BudgetService;
import it.db.budget.client.service.BudgetServiceAsync;
import it.db.budget.shared.bean.ProdottiEntity;
import it.db.budget.shared.bean.ProdottiScontrinoResponse;
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
	
	@UiField(provided = true)
    CellTable<ProdottiScontrinoResponse> cellTable = new CellTable<ProdottiScontrinoResponse>(10000);
	

	interface Binder extends UiBinder<Widget, ScontriniView> {
	}
	
	
	
	private ListDataProvider<ProdottiScontrinoResponse> cellTableProvider = new ListDataProvider<ProdottiScontrinoResponse>();
	
	@Inject
	ScontriniView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
		buildTendinaSupermercati();
		buildTendinaProdottiSpesa();

		colbottone.setMarginTop(25);
		
		totaleSpeso.setEnabled(false);

		prodottiContainer.setVisible(false);
		cellTable.setVisible(false);
		
		
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
		
		
		/* =========================== COSTRUZIONE TABELLA =========================== */
		final TextColumn<ProdottiScontrinoResponse> colProdotto = new TextColumn<ProdottiScontrinoResponse>() {
            @Override
            public String getValue(final ProdottiScontrinoResponse object) {
                return String.valueOf(object.getProdotto());
            }
        };
        cellTable.addColumn(colProdotto, "Prodotto");

        final TextColumn<ProdottiScontrinoResponse> colQuantita = new TextColumn<ProdottiScontrinoResponse>() {
            @Override
            public String getValue(final ProdottiScontrinoResponse object) {
                return String.valueOf(object.getQuantita());
            }
        };
        cellTable.addColumn(colQuantita, "Quantita'");
        
        final TextColumn<ProdottiScontrinoResponse> colPrezzoUnitario = new TextColumn<ProdottiScontrinoResponse>() {
            @Override
            public String getValue(final ProdottiScontrinoResponse object) {
                return String.valueOf(object.getPrezzoUnitario());
            }
        };
        cellTable.addColumn(colPrezzoUnitario, "Prezzo");
        
        final TextColumn<ProdottiScontrinoResponse> colSconto = new TextColumn<ProdottiScontrinoResponse>() {
            @Override
            public String getValue(final ProdottiScontrinoResponse object) {
                return String.valueOf(object.getPercentualeSconto());
            }
        };
        cellTable.addColumn(colSconto, "Sconto");
        
        final TextColumn<ProdottiScontrinoResponse> colPrezzoFinale = new TextColumn<ProdottiScontrinoResponse>() {
            @Override
            public String getValue(final ProdottiScontrinoResponse object) {
                return String.valueOf(object.getPrezzoDefinitivo());
            }
        };
        cellTable.addColumn(colPrezzoFinale, "Prezzo Finale");
        
        
        ButtonCell buttonCell = new ButtonCell(ButtonType.PRIMARY, IconType.MINUS);
        final com.google.gwt.user.cellview.client.Column<ProdottiScontrinoResponse, String> col4 = new com.google.gwt.user.cellview.client.Column<ProdottiScontrinoResponse, String>(buttonCell){

			@Override
			public String getValue(ProdottiScontrinoResponse object) {
				// TODO COMPLETARE
				return null;
			}
        	
        };

        col4.setFieldUpdater(new FieldUpdater<ProdottiScontrinoResponse, String>() {
            @Override
            public void update(int index, ProdottiScontrinoResponse object, String value) {
                Window.alert("Cliccata la riga: " + index + ". Oggetto: " + object.getProdotto());
            }
        });
        
        cellTable.addColumn(col4, "Cancella");
        
        
        
        
        
        
        
        
        cellTableProvider.addDataDisplay(cellTable);
	}

	
	@UiHandler("salvaButton")
	public void onSalvaButtonClick(final ClickEvent event) {
		if(idScontrino == null) {
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
						cellTable.setVisible(true);
						salvaButton.setText("Chiudi Scontrino");
						salvaButton.setType(ButtonType.SUCCESS);
					}
				});
				
			} else {
				com.google.gwt.user.client.Window.alert("Attenzione i campi Data Scontrino e Supermercato devono essere valorizzati!");
			}
		} else {
			SupermercatiEntity supermercatoSelected = supermercatiFound.get(select.getSelectedIndex());
			final BudgetServiceAsync service = GWT.create(BudgetService.class);
			
			BigDecimal totSpeso = BigDecimal.ZERO;
			
			if(totaleSpeso.getValue() != null && !totaleSpeso.getValue().trim().equalsIgnoreCase("")) {
				totSpeso = new BigDecimal(totaleSpeso.getValue().trim().replaceAll(",", "."));
			}

			service.editScontrino(idScontrino, dataSpesaTB.getText(), supermercatoSelected.getIdSupermercato(), totSpeso, new AsyncCallback<Void>() {
				@Override
				public void onFailure(Throwable caught) {
					com.google.gwt.user.client.Window.alert("Errore durante il salvataggio dello scontrino in fase di chiusura conto.");
				}
				@Override
				public void onSuccess(Void result) {
					service.chiudiScontrino(idScontrino, new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							com.google.gwt.user.client.Window.alert("Errore durante la chiusura dello scontrino.");
						}

						@Override
						public void onSuccess(Void result) {
							com.google.gwt.user.client.Window.alert("Scontrino chiuso correttamente.");
							getUiHandlers().sendToRicercaSpese();
						}
					});
				}
			});
		}
	}
	
	@UiHandler("addProduct")
	public void onAddProdottoClick(final ClickEvent event) {
		
		if(prodottiSpesa.getSelectedIndex()>0 && prezzoFinaleTB.getText() != null){
			BudgetServiceAsync service = GWT.create(BudgetService.class);
			
			BigDecimal sconto = BigDecimal.ZERO;
			if(scontoTB.getValue()!=null && !scontoTB.getValue().trim().equalsIgnoreCase("")) {
				sconto = new BigDecimal(scontoTB.getValue().replaceAll(",", "."));
			}
			
			service.addProdottoScontrino(idScontrino,
										 new BigDecimal(this.prodottiFound.get(prodottiSpesa.getSelectedIndex()).getIdProdottoSpesa()),
										 new BigDecimal(quantitaTB.getValue().replaceAll(",", ".")),
										 new BigDecimal(prezzoTB.getValue().replaceAll(",", ".")),
										 sconto,
										 new BigDecimal(prezzoFinaleTB.getValue().replaceAll(",", ".")),
										 new AsyncCallback<Void>() {

						@Override
						public void onFailure(Throwable caught) {
							com.google.gwt.user.client.Window.alert("Errore durante il salvataggio del prodotto. " + caught.getMessage());
						}
						@Override
						public void onSuccess(Void result) {
							//aggiorna il totale speso
							aggiornaTotaleSpeso(new BigDecimal(prezzoFinaleTB.getValue().replaceAll(",", ".")));
							cleanDataProducts();
							cleanDataScontrino();
							aggiornaListaScontrino();
							
						}
					});
		} else {
			com.google.gwt.user.client.Window.alert("Attenzione i campi Prodotto e Prezzo finale devono essere valorizzati!");
		}
	}
	
	private void aggiornaTotaleSpeso(BigDecimal aToAdd) {

		BigDecimal lTotSpeso = BigDecimal.ZERO;
		if(totaleSpeso.getValue() != null && !totaleSpeso.getValue().trim().equalsIgnoreCase("")) {
			lTotSpeso = new BigDecimal(totaleSpeso.getValue().replaceAll(",", "."));
		}
		totaleSpeso.setValue(lTotSpeso.add(aToAdd).toString()); 
	}
	
	private void cleanDataProducts() {
		prodottiSpesa.setSelectedIndex(0);
		quantitaTB.setValue("");
		prezzoTB.setValue("");
		scontoTB.setValue("");
		prezzoFinaleTB.setValue("");
		prodottiSpesa.setFocus(true);
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


	@Override
	public void cleanData() {
		cleanDataProducts();
		totaleSpeso.setValue("");
		dataSpesaTB.setValue("");
		select.setSelectedIndex(0);
		idScontrino = null;
		prodottiContainer.setVisible(false);
		cellTable.setVisible(false);
		cleanDataScontrino();
		salvaButton.setText("Salva");
		salvaButton.setType(ButtonType.PRIMARY);
	}

	private void cleanDataScontrino() {
		cellTableProvider.flush();
		cellTableProvider.getList().clear();
		cellTableProvider.flush();
	}
	
	private void aggiornaListaScontrino() {
		BudgetServiceAsync service = GWT.create(BudgetService.class);
		service.getListaProdottiScontrino(idScontrino, new AsyncCallback<List<ProdottiScontrinoResponse>>() {
			@Override
			public void onFailure(Throwable caught) {
				GWT.log("Errore durante il caricamento dei prodotti dello scontrino.", caught);
			}
			@Override
			public void onSuccess(List<ProdottiScontrinoResponse> result) {
				cellTableProvider.getList().addAll(result);
			}
		});
	}
	
	
}
