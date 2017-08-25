package view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Loty;

public class Trasy extends VerticalLayout implements View
{
    public Trasy()
    {
        Grid<Loty> gridTrasy;
        Label labelGridTitle;
        TextField textStartowe;
        TextField textDocelowe;
        DateField dateOdlot;
        Button buttonSzukaj;

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

        labelGridTitle = new Label("Wyszukiwarka");
        labelGridTitle.addStyleName("h2");
        labelGridTitle.addStyleName("colored");

        formLayout.addComponent(labelGridTitle);

        //grid

        footer = new HorizontalLayout();
        footer.setMargin(new MarginInfo(false, false, true, false));
        footer.setSpacing(true);

        gridTrasy = new Grid<>();
        gridTrasy.getEditor().setEnabled(true);
        gridTrasy.setWidth("1050");
        gridTrasy.setHeight("500");

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

        textStartowe = new TextField("Lotnisko startowe");
        textStartowe.setRequiredIndicatorVisible(true);

        textDocelowe = new TextField("Lotnisko docelowe");
        textDocelowe.setRequiredIndicatorVisible(true);

        dateOdlot = new DateField("Data odlotu");
        dateOdlot.setRequiredIndicatorVisible(true);

        buttonSzukaj = new Button("Szukaj");
        buttonSzukaj.addStyleName("primary");

        formLayout.addComponent(textStartowe);
        formLayout.addComponent(textDocelowe);
        formLayout.addComponent(dateOdlot);
        formLayout.addComponent(buttonSzukaj);
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
        gridTrasy.addColumn(Loty::getCenaBiletu).setCaption("Cena biletu");
        //gridTrasy.getColumn("Cena biletu").setEditable(false);


        footer.addComponent(gridTrasy);
        this.addComponent(footer);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent)
    {

    }
}
