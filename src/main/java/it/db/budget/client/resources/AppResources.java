package it.db.budget.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;

public interface AppResources extends ClientBundle {
    interface Normalize extends CssResource {
    }

    interface Style extends CssResource {
    }

    @Source("resource/css/demo.css")
    Normalize normalize();

    @Source("resource/css/prettify.css")
    Style style();
}
