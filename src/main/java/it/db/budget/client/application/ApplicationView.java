package it.db.budget.client.application;

import javax.inject.Inject;

import org.gwtbootstrap3.client.ui.NavbarCollapse;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

public class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
	
	@UiField
    SimplePanel contentContainer;
    @UiField
    NavbarCollapse navbarCollapse;
    
    
	interface Binder extends UiBinder<Widget, ApplicationView> {
	}

	
	@Inject
    ApplicationView(final Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }
	
	@Override
    public void setInSlot(final Object slot, final IsWidget content) {
        if (slot == ApplicationPresenter.SLOT_MAIN) {
            contentContainer.setWidget(content);
        } else {
            super.setInSlot(slot, content);
        }
    }
}