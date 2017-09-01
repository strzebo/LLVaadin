package view;

import com.vaadin.data.Binder;
import com.vaadin.data.HasValue;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.*;
import logic.Uzytkownik;
import logic.Main;

import java.sql.SQLException;

public class Konto extends VerticalLayout implements View
{
    public Konto()
    {
        Main main = new Main();
        FormLayout formLayout = new FormLayout();

        setMargin(true);
        setSpacing(true);

        formLayout.setMargin(false);
        formLayout.setWidth("35%");;
        formLayout.addStyleName("dark");

        addComponent(formLayout);
        setComponentAlignment(formLayout, Alignment.TOP_CENTER);

        HorizontalLayout footer = new HorizontalLayout();

        footer.setMargin(new MarginInfo(false, false, true, false));
        footer.setSpacing(true);

        Grid<Uzytkownik> gridUzytkownicy = new Grid<>();
        gridUzytkownicy.setWidth("1050");
        gridUzytkownicy.setHeight("500");
        try
        {
            gridUzytkownicy.setItems(main.getUzytkownikList(main.getUserType()));
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        
        gridUzytkownicy.setSelectionMode(Grid.SelectionMode.NONE);
        gridUzytkownicy.addColumn(Uzytkownik::getName).setCaption("Imię");
        gridUzytkownicy.addColumn(Uzytkownik::getLastName).setCaption("Nazwisko");
        gridUzytkownicy.addColumn(Uzytkownik::getEmail).setCaption("E-mail");
        gridUzytkownicy.addColumn(Uzytkownik::getPassword).setCaption("Hasło");
        gridUzytkownicy.addColumn(Uzytkownik::getAddress).setCaption("Adres");
        gridUzytkownicy.addColumn(Uzytkownik::getPhoneNumber).setCaption("Numer telefonu");
        gridUzytkownicy.addColumn(Uzytkownik::getIdNumber).setCaption("Numer dokumentu");
        gridUzytkownicy.addColumn(Uzytkownik::getType).setCaption("Typ konta");

        Binder<Uzytkownik> binder = new Binder<>(Uzytkownik.class);

        formLayout.addComponent(footer);

        footer.addComponent(gridUzytkownicy);

        this.addComponent(footer);
    }
    
    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }


}
