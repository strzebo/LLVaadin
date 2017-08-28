package view;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;

@StyleSheet({"http://fonts.googleapis.com/css?family=Lato"})

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
        navigator.addView("panelkierownika", new PanelKierownika());
        navigator.addView("panelsprzedawcy", new PanelSprzedawcy());
        navigator.addView("zmianadanych", new ZmianaDanych());
        navigator.addView("rejestracja", new Rejestracja());
        navigator.addView("rezerwacja", new Rezerwacja());
        navigator.addView("trasy", new Trasy());
        navigator.addView("historiaperacji", new HistoriaOperacji());
        navigator.addView("konto", new Konto());

        navigator.navigateTo("index");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {

    }

}
