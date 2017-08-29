package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import com.vaadin.ui.renderers.ButtonRenderer;
import logic.Loty;
import logic.Main;

import java.sql.SQLException;

public class Trasy extends VerticalLayout implements View
{
    public Trasy() throws SQLException
    {
        Main main = new Main();
        Grid<Loty> gridTrasy;


        HorizontalLayout footer;
        FormLayout formLayout;

        setSpacing(true);
        setMargin(true);

        formLayout = new FormLayout();
        formLayout. setMargin(false);
        formLayout.setWidth("35%");
        formLayout.addStyleName("dark");

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.TOP_CENTER);

        //grid

        footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(false, false, true, false));
        footer.setSpacing(true);

        gridTrasy = new Grid<>();
        gridTrasy.getEditor().setEnabled(true);
        gridTrasy.setWidth("1117px");
        gridTrasy.setHeight("500");
        gridTrasy.setItems(main.getLotyList());
        /* jak będzie jakiś pracownik to warunek:

            public bool mozeEdytowac()
            {
                if(login.cośtam.getType != uzytkownik)
                    return true;

                return false;
            }

            i wtedy :

            gridTrasy.getColumn("ID").setEditable(mozeEdytowac());

        */
        formLayout.addComponent(footer);

        gridTrasy.addColumn (Loty::getID).setCaption("Nr");
        //gridTrasy.getColumn("ID").setEditable(false);
        gridTrasy.addColumn(Loty::getLotniskoStartowe).setCaption("Lotnisko startowe");
        //gridTrasy.getColumn("Lotnisko startowe").setEditable(false);
        gridTrasy.addColumn(Loty::getLotniskoDocelowe).setCaption("Lotnisko docelowe");
        //gridTrasy.getColumn("Lotnisko docelowe").setEditable(false);
        gridTrasy.addColumn(Loty::getDataOdlotu).setCaption("Data odlotu");
        //gridTrasy.getColumn("Data odlotu").setEditable(false);
        gridTrasy.addColumn(Loty::getGodzinaOdlotu).setCaption("Godzina odlotu");
        //gridTrasy.getColumn("Godzina odlotu").setEditable(false);
        gridTrasy.addColumn(Loty::getDataPrzylotu).setCaption("Data przylotu");
        //gridTrasy.getColumn("Data przylotu").setEditable(false);
        gridTrasy.addColumn(Loty::getGodzinaPrzylotu).setCaption("Godzina przylotu");
        //gridTrasy.getColumn("Godzina przylotu").setEditable(false);

        gridTrasy.addColumn(loty -> "Zarezerwuj", new ButtonRenderer(clickEvent ->
        {
            Notification.show("zarezerwowane ziomeczku!");
        })).setCaption("Zarezerwuj") ;


        footer.addComponent(gridTrasy);
        //footer.addComponent(buttonZarezerwuj);
        this.addComponent(footer);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
