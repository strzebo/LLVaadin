package view;

import com.vaadin.annotations.StyleSheet;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

import javax.servlet.annotation.WebServlet;
import java.sql.SQLException;

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
        try
        {
            navigator.addView("rezerwacja", new Rezerwacja());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        try
        {
            navigator.addView("trasy", new Trasy());
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        navigator.addView("historiaperacji", new HistoriaOperacji());
        navigator.addView("konto", new Konto());

        if(navigator.getState().isEmpty() || navigator.getState().equals("login") || navigator.getState().equals("rejestracja"))
            navigator.navigateTo("index");
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet
    {

    }

}
