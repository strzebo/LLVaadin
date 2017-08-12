package view;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@Theme("mytheme")
public class MyUI extends UI
{

    @Override
    protected void init(VaadinRequest vaadinRequest)
    {
        Navigator navigator = new Navigator(this,this);

        navigator.addView("index", new Index());
        navigator.addView("kontakt", new Kontakt());
        navigator.addView("login", new Login());
        navigator.addView("paneladministratora", new PanelAdministratora());
        navigator.addView("paneluzytkownika", new PanelUzytkownika());
        navigator.addView("rejestracja", new Rejestracja());
        navigator.addView("rezerwacja", new Rezerwacja());
        navigator.addView("trasy", new Trasy());

        navigator.navigateTo("login");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {

    }

}